package np.com.emoji_based_interactive_challenge_guide.service

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Executors
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType

class FaceDetectionManager(private val context: Context) {

    private var cameraProvider: ProcessCameraProvider? = null
    private val executor = Executors.newSingleThreadExecutor()

    private val _detectedMood = MutableStateFlow<MoodType?>(null)
    val detectedMood: StateFlow<MoodType?> = _detectedMood

    private val _isDetecting = MutableStateFlow(false)
    val isDetecting: StateFlow<Boolean> = _isDetecting

    private val _error = MutableStateFlow<String?>(null)
    val detectionError: StateFlow<String?> = _error

    private val _status = MutableStateFlow("Idle")
    val cameraStatus: StateFlow<String> = _status

    private var lastTime = 0L

    private val detector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()
    )

    @SuppressLint("UnsafeOptInUsageError")
    fun startCamera(owner: LifecycleOwner, previewView: PreviewView) {
        val future = ProcessCameraProvider.getInstance(context)

        future.addListener({
            cameraProvider = future.get()

            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }

            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            analysis.setAnalyzer(executor) { imageProxy ->
                process(imageProxy)
            }

            val selector = CameraSelector.DEFAULT_FRONT_CAMERA

            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(owner, selector, preview, analysis)

            _status.value = "Running"
            _isDetecting.value = true

        }, ContextCompat.getMainExecutor(context))
    }

    private fun process(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage == null) {
            imageProxy.close()
            return
        }

        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        val now = System.currentTimeMillis()

        if (now - lastTime < 1000) {
            imageProxy.close()
            return
        }

        lastTime = now

        detector.process(image)
            .addOnSuccessListener { faces ->
                if (faces.isNotEmpty()) {
                    _detectedMood.value = analyze(faces[0])
                    _status.value = "Face detected"
                } else {
                    _status.value = "No face"
                }
            }
            .addOnFailureListener {
                _error.value = it.message
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun analyze(face: Face): MoodType {
        val smile = face.smilingProbability ?: 0f
        val left = face.leftEyeOpenProbability ?: 0f
        val right = face.rightEyeOpenProbability ?: 0f

        return when {
            smile > 0.7 -> MoodType.HAPPY
            smile < 0.2 && left < 0.4 -> MoodType.TIRED
            smile < 0.3 -> MoodType.SAD
            smile in 0.3..0.6 -> MoodType.THINKING
            else -> MoodType.COOL
        }
    }

    fun stopCamera() {
        cameraProvider?.unbindAll()
        _isDetecting.value = false
        _status.value = "Stopped"
    }

    fun release() {
        executor.shutdown()
        detector.close()
    }
}
