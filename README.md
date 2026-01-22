# 🤰 Pregnancy Vitals Tracker

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge\&logo=android\&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge\&logo=kotlin\&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge\&logo=jetpackcompose\&logoColor=white)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)

### A modern Android application for tracking pregnancy vitals with intelligent reminders

**📌 Assignment Project for *Janitri Innovations Private Limited***

[Features](#-features) • [Screenshots](#-screenshots) • [Architecture](#-architecture) • [Tech Stack](#-tech-stack) • [APK](#-apk-download) • [Setup](#-installation--setup)

</div>

---

## 📋 Overview

**Pregnancy Vitals Tracker** is a modern Android application designed to help expectant mothers log, monitor, and stay consistent with essential pregnancy health vitals.

The application was developed as part of a technical assignment for **Janitri Innovations Private Limited**, demonstrating best practices in **Android development**, **MVVM architecture**, **Jetpack Compose UI**, **Room persistence**, and **WorkManager-based reminders**.

The app ensures:

* Simple vitals logging
* Real-time UI updates
* Reliable background reminders
* Clean, maintainable architecture

---

## ✨ Features

### 🩺 Health Tracking

* Log **Blood Pressure (Systolic / Diastolic)**
* Track **Heart Rate**
* Record **Weight**
* Monitor **Baby Kicks Count**

### 🔄 Real-Time Updates

* Instant UI updates using **StateFlow**
* Automatic list refresh on data insertion

### 💾 Offline-First Storage

* Data stored locally using **Room Database**
* Works without internet connectivity

### ⏰ Smart Reminder System

* Automated reminders every **5 hours**
* Implemented using **WorkManager**
* Reliable even after app kill or device reboot

### 🔔 Notifications

* Notification Title:
  **“Time to log your vitals!”**
* Message:
  **“Stay on top of your health. Please update your vitals now!”**
* Tapping the notification opens the app directly

### 🎨 UI & UX

* Built entirely with **Jetpack Compose**
* Material Design 3 styling
* Floating Action Button for quick logging
* Clean empty-state handling for first-time users

---

## 📱 Screenshots

### App UI & Flow

<div align="center">

<img src="https://raw.githubusercontent.com/Sourasamanta/ScreenShots/main/PregnancyTracker/Ui%201.jpeg" width="250" />
<img src="https://raw.githubusercontent.com/Sourasamanta/ScreenShots/main/PregnancyTracker/Ui%202.jpeg" width="250" />
<img src="https://raw.githubusercontent.com/Sourasamanta/ScreenShots/main/PregnancyTracker/Ui%203.jpeg" width="250" />

</div>

### Demo GIF

<div align="center">

<img src="https://raw.githubusercontent.com/Sourasamanta/ScreenShots/main/PregnancyTracker/Demo.gif" width="300" />

</div>

📁 Screenshot Repository:
👉 [https://github.com/Sourasamanta/ScreenShots/tree/main/PregnancyTracker](https://github.com/Sourasamanta/ScreenShots/tree/main/PregnancyTracker)

---

## 🏛️ Architecture

The app follows **Clean Architecture with MVVM**:

```
┌──────────────────────────────────────────┐
│        Presentation Layer (UI)           │
│  Jetpack Compose + ViewModels            │
└──────────────┬───────────────────────────┘
               │
┌──────────────▼───────────────────────────┐
│           Domain / Logic Layer           │
│        StateFlow, Validation             │
└──────────────┬───────────────────────────┘
               │
┌──────────────▼───────────────────────────┐
│             Data Layer                   │
│    Room DB, Repository, DAO              │
└──────────────────────────────────────────┘
               │
┌──────────────▼───────────────────────────┐
│         Background Layer                 │
│   WorkManager, Notifications             │
└──────────────────────────────────────────┘
```

### Why this architecture?

* Clear separation of concerns
* Lifecycle-safe state handling
* Scalable and testable codebase
* Industry-standard Android practices

---

## 🛠️ Tech Stack

### Language & UI

* **Kotlin**
* **Jetpack Compose**
* **Material Design 3**

### Architecture & State

* **MVVM**
* **ViewModel**
* **StateFlow**
* **Coroutines**

### Persistence & Background

* **Room Database**
* **WorkManager**
* **Notification Channels**

### Build & SDK

* **Min SDK**: 24
* **Target SDK**: 36
* **Gradle**: 8.13.2
* **Kotlin**: 2.1.0

---

## 📥 APK Download

📦 **APK is available for direct installation**

👉 **[Download APK](APK_LINK_HERE)**
*(Replace with actual APK link once uploaded)*

> Enable **“Install from Unknown Sources”** before installing.

---

## 📥 Installation & Setup (For Developers)

```bash
git clone https://github.com/yourusername/pregnancy-vitals-tracker.git
cd pregnancy-vitals-tracker
./gradlew assembleDebug
```

Open the project in **Android Studio Hedgehog or later**.

---

## 🧪 Assignment Requirements Checklist

✅ LazyColumn for vitals list
✅ Compose Dialog for adding vitals
✅ Room database persistence
✅ StateFlow for live updates
✅ WorkManager reminder every 5 hours
✅ Notification opens the app on click
✅ MVVM architecture
✅ Jetpack Compose UI

✔️ **All assignment requirements fully implemented**

---

## 🧾 About the Assignment

This project was developed as part of an **Android technical assignment** provided by:

### 🏢 **Janitri Innovations Private Limited**

The goal was to evaluate:

* Android fundamentals
* Modern UI development
* Clean architecture
* Background task handling
* Data persistence & state management

---

## 👨‍💻 Author

**Souras Samanta**

* GitHub: [https://github.com/Sourasamanta](https://github.com/Sourasamanta)
* LinkedIn: *(add if you want)*
* Email: *(optional)*

---

## ⭐ Final Note

If you are reviewing this as part of a technical evaluation:

* The project strictly follows **Android best practices**
* Uses **production-ready architecture**
* Designed for **scalability and maintainability**

---

<div align="center">

**⭐ Star the repository if you found it useful**

Built with care for real-world Android development

</div>
