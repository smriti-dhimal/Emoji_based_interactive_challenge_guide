package np.com.emoji_based_interactive_challenge_guide.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.service.FaceDetectionManager
import np.com.emoji_based_interactive_challenge_guide.ui.components.EmojiButton

@Composable
fun FaceDetectionScreen(
    onMoodDetected: (MoodType) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val faceDetectionManager = remember { FaceDetectionManager(context) }

    var hasPermission by remember { mutableStateOf(false) }
    var cameraStarted by remember { mutableStateOf(false) }
    var previewViewRef by remember { mutableStateOf<PreviewView?>(null) }

    // Permission launcher
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }

    // Check permission
    LaunchedEffect(Unit) {
        hasPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    val detectedMood by faceDetectionManager.detectedMood.collectAsStateWithLifecycle()
    val isDetecting by faceDetectionManager.isDetecting.collectAsStateWithLifecycle()
    val error by faceDetectionManager.detectionError.collectAsStateWithLifecycle()
    val status by faceDetectionManager.cameraStatus.collectAsStateWithLifecycle()

    // Send result
    LaunchedEffect(detectedMood) {
        detectedMood?.let { onMoodDetected(it) }
    }

    DisposableEffect(Unit) {
        onDispose {
            faceDetectionManager.stopCamera()
            faceDetectionManager.release()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Face Detection", fontSize = 26.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f),
            contentAlignment = Alignment.Center
        ) {

            if (hasPermission) {
                AndroidView(
                    factory = {
                        PreviewView(it).apply {
                            previewViewRef = this
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Button(onClick = {
                    launcher.launch(Manifest.permission.CAMERA)
                }) {
                    Text("Grant Permission")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Status: $status", color = Color.Gray)

        error?.let {
            Text("Error: $it", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (isDetecting) {
            CircularProgressIndicator()
        }

        detectedMood?.let {
            Text("Mood: $it", color = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))

        EmojiButton(
            text = if (cameraStarted) "Stop Camera" else "Start Camera",
            emoji = if (cameraStarted) "⏹️" else "▶️",
            onClick = {
                if (cameraStarted) {
                    faceDetectionManager.stopCamera()
                    cameraStarted = false
                } else {
                    previewViewRef?.let {
                        faceDetectionManager.startCamera(lifecycleOwner, it)
                        cameraStarted = true
                    }
                }
            },
            enabled = hasPermission
        )
    }
}