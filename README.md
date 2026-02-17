# Quizie 🧠

Quizie is a modern, sleek Android application designed to help users track their quiz progress and
performance. Built with **Jetpack Compose** and **Material Design 3**, it provides a fluid and
intuitive user experience for all quiz enthusiasts.

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-blue.svg?style=flat&logo=android)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Material%20Design-3.0-green.svg?style=flat&logo=materialdesign)](https://m3.material.io/)
[![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg?style=flat&logo=android)](https://www.android.com/)

---

## ✨ Features

- **Personalized Dashboard**: A clean and modern overview of your performance.
- **Progress Tracking**: Real-time progress bar reflecting your accuracy across quizzes.
- **Detailed Statistics**: Track the number of questions attempted and right answers at a glance.
- **Dynamic User Profile**: Easily update your name to personalize your experience.
- **Modern UI/UX**: Fully built with Jetpack Compose following Material Design 3 guidelines.
- **Light/Dark Mode Support**: Adapts perfectly to your system settings.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Networking**: [Ktor](https://ktor.io/)
- **Dependency Injection**: [Koin](https://insert-koin.io/)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Architecture**: Clean Architecture & MVVM (Model-View-ViewModel)
- **Design**: [Material Design 3](https://m3.material.io/)
- **Build System**: Gradle Kotlin DSL (.kts)
- **Dependency Management**: Gradle Version Catalogs

## 📂 Project Structure

```text
app/src/main/java/com/actaks/quizie/
├── presentation/
│   ├── dashboard/          # Dashboard screen and its state
│   │   ├── components/     # Reusable UI components (UserStatisticsCard, etc.)
│   │   ├── DashboardScreen.kt
│   │   └── DashboardState.kt
│   └── theme/              # Material 3 Theme definitions (Color, Type, Theme)
└── MainActivity.kt         # Entry point of the application
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Ladybug (2024.2.1) or newer
- JDK 17
- Android SDK 36 (API Level 36)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Act-Aks/quizie.git
   ```
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device.

## 🤝 Contributing

Contributions are welcome! If you have suggestions for improvements or new features, feel free to
open an issue or submit a pull request.

---

Made with ❤️ by [Akash Srivastava](https://github.com/actaks)
