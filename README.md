<<<<<<< HEAD
# Emoji-Based Interactive Challenge Guide

A modern Android application that provides mood-based challenges and activities to help users improve their emotional well-being through interactive tasks.

## 🎯 Features

### Core Functionality
- **Mood Detection**: Users can select their current mood (Happy, Sad, Cool, Thinking, Tired)
- **Personalized Challenges**: Each mood type has tailored challenges with specific tasks
- **Progress Tracking**: Users earn points and track their completed challenges
- **Achievement System**: Visual rewards and badges for completed challenges

### Technical Features
- **MVVM Architecture**: Clean separation of concerns with Repository pattern
- **Modern UI**: Material Design 3 with custom theming
- **Dark/Light Mode**: Automatic theme switching based on system preferences
- **Responsive Design**: Optimized for various screen sizes
- **Jetpack Compose**: Modern declarative UI framework

## 🏗️ Architecture

### MVVM Pattern
```
├── data/
│   ├── models/          # Data models (User, Challenge, Task)
│   └── repository/      # Data repositories (UserRepository, ChallengeRepository)
├── viewmodel/          # ViewModels (AuthViewModel, ChallengeViewModel)
├── ui/
│   ├── components/     # Reusable UI components
│   ├── screens/        # Screen composables
│   ├── theme/          # Theme, colors, typography, shapes
│   └── navigation/     # Navigation setup
└── MainActivity.kt     # Main activity
```

### Key Components

#### Data Layer
- **User Model**: Stores user information, points, and progress
- **Challenge Model**: Contains challenge details, tasks, and mood types
- **Task Model**: Individual tasks within challenges
- **Repository Pattern**: Manages data flow and business logic

#### UI Layer
- **Theme System**: Comprehensive theming with mood-based colors
- **Reusable Components**: EmojiButton, ChallengeCard, TaskCard, etc.
- **Screen Composables**: Individual screens for each app section

#### ViewModel Layer
- **AuthViewModel**: Handles authentication and user management
- **ChallengeViewModel**: Manages challenge state and progress

## 🎨 UI/UX Design

### Theme System
- **Mood-Based Colors**: Each mood has its own color palette
- **Material Design 3**: Follows latest Material Design guidelines
- **Custom Typography**: Optimized text styles for readability
- **Responsive Shapes**: Consistent corner radius and spacing

### User Flow
1. **Splash Screen**: App introduction and loading
2. **Login/Register**: User authentication
3. **Mood Selection**: Choose current emotional state
4. **Challenge**: Complete mood-appropriate tasks
5. **Results**: View earned points and achievements
6. **Progress**: Track overall progress and start new challenges

## 🛠️ Technologies Used

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit
- **Navigation Component**: Screen navigation
- **ViewModel**: State management
- **Material Design 3**: UI/UX design system
- **Coroutines**: Asynchronous programming

## 📱 Screens

### 1. Splash Screen
- Animated welcome screen
- App branding and introduction
- Automatic navigation after delay

### 2. Login Screen
- Username and password input
- Form validation
- Error handling
- Navigation to registration

### 3. Registration Screen
- User account creation
- Form validation
- Password confirmation
- Navigation back to login

### 4. Mood Detection Screen
- Visual mood selection
- Emoji-based interface
- Multiple mood options
- Navigation to challenges

### 5. Challenge Screen
- Task progression
- Interactive task cards
- Progress indicators
- Points display

### 6. Result Screen
- Challenge completion celebration
- Points earned display
- Achievement badges
- Navigation options

### 7. Progress Screen
- Overall progress tracking
- Completed challenges list
- Points summary
- New challenge option

## 🎯 Challenge System

### Mood Types
- **Happy** 😊: Positive and uplifting activities
- **Sad** 💙: Comforting and gentle tasks
- **Cool** 😎: Confidence-boosting challenges
- **Thinking** 🤔: Mind-stimulating activities
- **Tired** 😴: Relaxing and restful tasks

### Challenge Structure
- Multiple tasks per challenge
- Progressive difficulty
- Point-based rewards
- Visual progress tracking

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Kotlin 1.9.0 or later
- Android SDK API 24+ (Android 7.0)
- Gradle 8.0+

### Installation
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the application

### Build Configuration
```kotlin
android {
    compileSdk = 36
    defaultConfig {
        minSdk = 24
        targetSdk = 36
    }
}
```

## 🧪 Testing

### Unit Tests
- ViewModel logic testing
- Repository function testing
- Data model validation

### UI Tests
- Compose UI testing
- Navigation flow testing
- User interaction testing

## 📈 Future Enhancements

### Planned Features
- **Face Detection Integration**: Camera-based mood detection
- **Social Features**: Share challenges with friends
- **Advanced Analytics**: Detailed mood tracking
- **Custom Challenges**: User-created challenges
- **Notifications**: Reminders and motivation
- **Cloud Sync**: Cross-device synchronization

### Technical Improvements
- **Dependency Injection**: Hilt integration
- **Database**: Room persistence
- **Network Layer**: Remote data synchronization
- **Modularization**: Feature-based modules

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions, please open an issue in the repository.

---

**Built with ❤️ using Jetpack Compose and Material Design 3**
=======
# Emoji Based Interactive Challenge Guide

Android/Kotlin project with **face detection** using ML Kit.  
Built for interactive engagement and real-time facial analysis.

## Features
- Real-time face detection
- Jetpack Compose UI
- Modular Android/Kotlin codebase
>>>>>>> 57fcc0bd67338aff22e19334baebc03c5454dfdd
