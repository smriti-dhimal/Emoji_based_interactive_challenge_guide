# Emoji-Based Interactive Challenge Guide

A modern Android application that provides mood-based challenges and activities to help users improve their emotional well-being through interactive tasks.

## 🎯 Features

### Core Functionality
- **Mood Selection**: Users can select their current mood (Happy, Sad, Cool, Thinking, Tired)
- **Personalized Challenges**: Each mood type has tailored challenges with specific tasks
- **Progress Tracking**: Users earn points and track their completed challenges
- **Comprehensive Achievement System**: 10 unique badges with smart unlocking criteria
- **Dual Progress Display**: Separate sections for achievements and completed challenges

### Technical Features
- **MVVM Architecture**: Clean separation of concerns with Repository pattern
- **Modern UI**: Material Design 3 with custom theming
- **Responsive Design**: Optimized for various screen sizes
- **Jetpack Compose**: Modern declarative UI framework
- **Perfect Point Calculation**: Accurate point tracking with answer validation

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
3. **Mood Selection**: Choose current emotional state with face detection integration
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

### 4. Mood Selection Screen
- Visual mood selection
- Emoji-based interface
- Multiple mood options
- Face detection integration for automatic mood selection
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
- Overall progress tracking with statistics
- Recent Achievements section (badge display)
- Completed Challenges section (challenge history)
- Points summary and streak tracking
- New challenge option

## 🎯 Challenge System

### Mood Types
- **Happy** 😊: Positive and uplifting activities
- **Sad** 💙: Comforting and gentle tasks
- **Cool** 😎: Confidence-boosting challenges
- **Thinking** 🤔: Mind-stimulating activities
- **Tired** 😴: Relaxing and restful tasks

### Challenge Structure
- 4 tasks per challenge (Puzzle, Creative, Social, Puzzle mix)
- Progressive difficulty (Easy → Medium → Hard)
- Point-based rewards (Easy: 5pts, Medium: 10pts, Hard: 15pts)
- Visual progress tracking with answer validation
- Skip functionality with no point penalties

## 🏆 Achievement System

### Achievement Types
- **First Steps** - Complete first challenge (🎯)
- **Rising Star** - Earn 50 total points (⭐)
- **Champion** - Earn 100 total points (🏆)
- **On Fire** - Complete 3 challenges in a row (🔥)
- **Unstoppable** - Complete 7 challenges in a row (💥)
- **Emotion Master** - Complete all mood types (🌈)
- **Puzzle Master** - Complete 10 puzzle tasks (🧩)
- **Creative Genius** - Complete 10 creative tasks (🎨)
- **Social Butterfly** - Complete 10 social tasks (🦋)
- **Challenge Warrior** - Complete 10 total challenges (⚔️)

### Achievement Features
- Smart unlocking based on user progress
- Beautiful badge cards with emoji icons
- Point rewards for each achievement
- Recent achievements display in Progress Screen
- Progress tracking for all achievement types

## 📸 Face Detection

### Implemented Features
- **Camera-Based Mood Detection**: Face detection to choose emoji according to mood
- **Automatic Emoji Selection**: Detect facial expressions and automatically select appropriate mood emoji


