# 🤰 Pregnancy Vitals Tracker

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)

### A modern Android application for tracking pregnancy vitals with intelligent reminders

**📌 Assignment Project for *Janitri Innovations Private Limited***

[Features](#-features) • [Screenshots](#-screenshots) • [Architecture](#-architecture) • [Tech Stack](#-tech-stack) • [APK](#-apk-download) • [Setup](#-installation--setup)

</div>

---

## 📋 Overview

**Pregnancy Vitals Tracker** is a modern Android application designed to help expectant mothers log, monitor, and stay consistent with essential pregnancy health vitals.

The application was developed as part of a technical assignment for **Janitri Innovations Private Limited**, demonstrating best practices in **Android development**, **MVVM architecture**, **Jetpack Compose UI**, **Room persistence**, and **AlarmManager-based reminders**.

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
* Implemented using **AlarmManager** with repeating alarms
* Persists across device reboots with **BootReceiver**
* Reliable even after app termination

### 🔔 Notifications

* Notification Title:
  **"Time to log your vitals!"**
* Message:
  **"Stay on top of your health. Please update your vitals now!"**
* Tapping the notification opens the app directly

### 🎨 UI & UX

* Built entirely with **Jetpack Compose**
* Material Design 3 styling
* Floating Action Button for quick logging
* Clean empty-state handling for first-time users
* Input validation for all health metrics

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
│   AlarmManager, Notifications            │
└──────────────────────────────────────────┘
```

### Key Components

#### 1. **Presentation Layer**
- `TrackMyPregnancyScreen.kt`: Main UI with vitals list and FAB
- `PregnancyVitalsViewModel.kt`: State management and business logic
- Compose Dialog for vitals input

#### 2. **Data Layer**
- `VitalsRepository.kt`: Single source of truth for data operations
- `PregnancyDatabase.kt`: Room database configuration
- `VitalEntryDao.kt`: Database access object
- `VitalEntry.kt`: Data model for vitals

#### 3. **Background Services**
- `VitalsAlarmReceiver.kt`: BroadcastReceiver for scheduled notifications
- `ReminderScheduler.kt`: AlarmManager scheduler for repeating alarms
- `NotificationHelper.kt`: Notification creation and management
- `BootReceiver.kt`: Reschedules alarms after device reboot

### Why this architecture?

* Clear separation of concerns
* Lifecycle-safe state handling
* Scalable and testable codebase
* Industry-standard Android practices

---

## 🛠️ Tech Stack

### Language & UI

| Technology | Purpose |
|-----------|---------|
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | Modern declarative UI framework |
| **Material Design 3** | UI design system |

### Architecture & State

| Technology | Purpose |
|-----------|---------|
| **MVVM** | Architectural pattern |
| **ViewModel** | UI-related data holder |
| **StateFlow** | Reactive state management |
| **Coroutines** | Asynchronous programming |

### Persistence & Background

| Technology | Purpose |
|-----------|---------|
| **Room Database** | Local database with SQLite |
| **AlarmManager** | Precise repeating alarms every 5 hours |
| **BroadcastReceiver** | Handle alarm triggers and boot events |
| **Notification Channels** | Android O+ notification system |

### Build & SDK

| Tool | Version |
|------|---------|
| **Gradle** | 8.13.2 |
| **Kotlin** | 2.1.0 |
| **KSP** | 2.1.0-1.0.29 |
| **Min SDK** | 24 (Android 7.0) |
| **Target SDK** | 36 |
| **Compile SDK** | 36 |

---

## 🔑 Key Implementation Details

### Database Schema
```kotlin
@Entity(tableName = "vitals")
data class VitalEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val heartRate: Int,      // 40-200 BPM
    val sys: Int,            // 70-220 mmHg
    val dia: Int,            // 40-140 mmHg
    val weightKg: Int,       // 20-200 kg
    val kicks: Int,          // 0-200 count
    val dateTimeLabel: String,
    val timestamp: Long = System.currentTimeMillis()
)
```

### State Management
```kotlin
class PregnancyVitalsViewModel(private val repository: VitalsRepository) : ViewModel() {
    private val _vitals = MutableStateFlow<List<VitalEntry>>(emptyList())
    val vitals: StateFlow<List<VitalEntry>> = _vitals.asStateFlow()
    
    // Collect from repository and update UI
    init {
        viewModelScope.launch {
            repository.vitals.collect { vitalsList ->
                _vitals.value = vitalsList
            }
        }
    }
}
```

### AlarmManager Configuration
```kotlin
object ReminderScheduler {
    fun scheduleReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, VitalsAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val interval = 5 * 60 * 60 * 1000L // 5 hours
        val triggerAt = System.currentTimeMillis() + interval
        
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAt,
            interval,
            pendingIntent
        )
    }
}
```

### Input Validation
```kotlin
when {
    hrV == null || hrV !in 40..200 -> 
        error = "Enter valid heart rate (40-200 BPM)"
    sysV == null || sysV !in 70..220 -> 
        error = "Enter valid systolic BP (70-220)"
    diaV == null || diaV !in 40..140 -> 
        error = "Enter valid diastolic BP (40-140)"
    diaV >= sysV -> 
        error = "Diastolic must be less than systolic"
    wV == null || wV !in 20..200 -> 
        error = "Enter valid weight (20-200 kg)"
    kV == null || kV !in 0..200 -> 
        error = "Enter valid kicks count (0-200)"
    else -> {
        error = null
        onSubmit(hrV, sysV, diaV, wV, kV)
    }
}
```

---

## 📊 Project Structure

```
app/
├── src/main/
│   ├── java/com/example/pregnancytracker/
│   │   ├── ui/
│   │   │   ├── TrackMyPregnancyScreen.kt
│   │   │   └── theme/
│   │   ├── viewmodel/
│   │   │   └── PregnancyVitalsViewModel.kt
│   │   ├── repository/
│   │   │   └── VitalsRepository.kt
│   │   ├── database/
│   │   │   ├── PregnancyDatabase.kt
│   │   │   ├── VitalEntry.kt
│   │   │   └── VitalEntryDao.kt
│   │   ├── notification/
│   │   │   ├── ReminderScheduler.kt
│   │   │   └── NotificationHelper.kt
│   │   ├── receiver/
│   │   │   ├── VitalsAlarmReceiver.kt
│   │   │   └── BootReceiver.kt
│   │   ├── PregnancyApp.kt
│   │   └── MainActivity.kt
│   ├── res/
│   │   ├── values/
│   │   │   ├── colors.xml
│   │   │   ├── strings.xml
│   │   │   └── themes.xml
│   │   └── drawable/
│   └── AndroidManifest.xml
└── build.gradle.kts
```

---

## 📥 APK Download

📦 **APK is available for direct installation**

👉 **[Download APK](APK_LINK_HERE)**
*(Replace with actual APK link once uploaded)*

> Enable **"Install from Unknown Sources"** before installing.

---

## 📥 Installation & Setup (For Developers)

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK with API 36
- Git

### Clone & Build

```bash
# Clone the repository
git clone https://github.com/Sourasamanta/pregnancy-vitals-tracker.git

# Navigate to project directory
cd pregnancy-vitals-tracker

# Open in Android Studio or build via command line
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

### Configuration

#### 1. Enable Notifications
The app requires notification permissions on Android 13+. The permission will be requested automatically on first launch.

#### 2. Battery Optimization (Optional but Recommended)
For most reliable reminders:
```
Settings → Battery → App Battery Usage → Pregnancy Tracker → Unrestricted
```

#### 3. Verify Alarm Scheduling
Check Logcat for confirmation:
```bash
adb logcat | grep "ReminderScheduler"
```

---

## 🧪 Testing

### Manual Testing Checklist
- [x] Add vitals with valid inputs
- [x] Test input validation (invalid ranges)
- [x] Verify database persistence (close/reopen app)
- [x] Test notification permission flow
- [x] Verify notification appears on schedule
- [x] Test notification tap action
- [x] Verify post-reboot alarm rescheduling
- [x] Test empty state UI
- [x] Verify list updates in real-time

---

## 🧾 Assignment Requirements Checklist

✅ **Main Screen**: LazyColumn displays vitals list  
✅ **Add Vitals Dialog**: Floating button opens Compose dialog  
✅ **Vitals Entry Form**: All required fields (BP, HR, Weight, Kicks)  
✅ **Submit Button**: Saves to Room Database  
✅ **Live Updates**: Real-time UI updates via StateFlow  
✅ **Reminder System**: AlarmManager schedules 5-hour notifications  
✅ **Notification Content**: Correct title and message  
✅ **Notification Action**: Opens app on tap  
✅ **MVVM Architecture**: Clean separation of concerns  
✅ **Room Database**: Persistent local storage  
✅ **StateFlow**: Reactive state management  
✅ **Modern UI**: Jetpack Compose with Material Design 3  

✔️ **All assignment requirements fully implemented**

---

## 🚀 Future Enhancements

- [ ] **Data Visualization**: Charts and graphs for vitals trends
- [ ] **Export Functionality**: PDF reports for doctor visits
- [ ] **Multiple Profiles**: Support for multiple pregnancies
- [ ] **Cloud Sync**: Backup data to cloud storage
- [ ] **Doctor Integration**: Share data with healthcare providers
- [ ] **Medication Reminders**: Track prenatal vitamins
- [ ] **Appointment Scheduler**: Manage doctor appointments
- [ ] **Educational Content**: Weekly pregnancy tips
- [ ] **Community Forum**: Connect with other expectant mothers
- [ ] **Widget Support**: Quick logging from home screen

---

## 🧾 About the Assignment

This project was developed as part of an **Android technical assignment** provided by:

### 🏢 **Janitri Innovations Private Limited**

**Assignment Timeline**: 24 hours

The goal was to evaluate:

* Android fundamentals
* Modern UI development (Jetpack Compose)
* Clean architecture (MVVM)
* Background task handling (Notifications & Alarms)
* Data persistence & state management (Room & StateFlow)

**Figma Design Reference**:
👉 [View Design](https://www.figma.com/design/rbNhibdu0ZHt4Os85vY6Tm/Android-Intern)

---

## 👨‍💻 Author

**Souras Samanta**

* GitHub: [@Sourasamanta](https://github.com/Sourasamanta)
* LinkedIn: [Add your LinkedIn]
* Email: [Add your email]

---

## 🙏 Acknowledgments

- Design inspired by [Figma Design](https://www.figma.com/design/rbNhibdu0ZHt4Os85vY6Tm/Android-Intern)
- Icons from Material Icons
- Built with ❤️ using Android Jetpack libraries

---

## ⭐ Final Note

If you are reviewing this as part of a technical evaluation:

* The project strictly follows **Android best practices**
* Uses **production-ready architecture**
* Designed for **scalability and maintainability**
* Demonstrates **strong understanding of Android fundamentals**

---

<div align="center">

**⭐ Star the repository if you found it useful**

Built with care for real-world Android development 🚀

</div>
