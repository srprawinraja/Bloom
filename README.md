# Bloom Android App

Bloom is a companion app for managing PCOS, tracking cycle, meals, movement, water, relaxation, and sleep.

## 🚀 Setup Instructions

1.  **Clone/Open**: Open the project in the latest version of **Android Studio**.
2.  **JDK**: Ensure you are using **Java 11** or higher.
3.  **Gradle Sync**: Let the Gradle sync finish to download all dependencies (including Google Mobile Ads SDK).
4.  **AdMob Configuration**:
    *   The App ID is already configured in `AndroidManifest.xml`.
    *   For testing, the app uses Google official test IDs.
5.  **Run**: Build and run the app on an Emulator or a physical device.
    *   *Note*: To see App Open ads, you must complete the onboarding process at least once.

## 🏗 Architecture

The app follows modern Android development practices:
*   **Single Activity Architecture**: Uses `MainActivity.kt` as the entry point.
*   **Jetpack Compose**: 100% declarative UI.
*   **MVVM Pattern**: ViewModels handle state logic (e.g., `OnBoardingViewModel`).
*   **Navigation Compose**: Handles transitions between Onboarding and the multi-tab Home Screen.
*   **Persistent Storage**: Uses `SharedPreferences` (via `UserPreferences.kt`) to store user identity and onboarding status.

## 💰 Ad Implementation Approach

### 1. Adaptive Banner Ad
*   **Component**: A reusable `BannerAd` Composable built with `AndroidView`.
*   **Adaptive Size**: Dynamically calculates the optimal ad size based on the device width and orientation.
*   **Placement**: Integrated into the **Dashboard Screen** to ensure monetization without disrupting the user flow.

### 2. App Open Ad
*   **Manager**: `AppOpenAdManager.kt` handles preloading and showing logic.
*   **Lifecycle Aware**: Uses `DefaultLifecycleObserver` and `ProcessLifecycleOwner` to detect when the app moves from background to foreground.
*   **Cold-Start Optimized**: Includes a queuing mechanism (`showAdOnNextLoad`) to show the ad automatically even if it finishes loading *after* the app has started.
*   **Onboarding Protection**: Ads are strictly disabled during the initial onboarding flow to ensure a seamless first-user experience.

## 🆔 Test Ad IDs Used

The app is currently in **Test Mode** (`isTestMode = true` in `AdsConfig.kt`).

| Ad Type | Test Ad Unit ID |
| :--- | :--- |
| **App Open** | `ca-app-pub-3940256099942544/9257395921` |
| **Banner** | `ca-app-pub-3940256099942544/9214589741` |

*Sample App ID used for testing: `ca-app-pub-3940256099942544~3347511713`*

## ⚠️ Assumptions & Limitations

1.  **Account Warm-up**: Real ads (Production IDs) may not appear immediately after account creation. AdMob typically requires 24–48 hours to start serving.
2.  **Test Device Registration**: For physical devices, you may need to add your device's hashed ID to `BloomApplication.kt` if test ads don't appear.
3.  **Onboarding Gating**: The App Open ad will only trigger once the user has clicked "Enter Bloom" on the final onboarding screen.
4.  **Internet Dependency**: Ads require an active network connection. Failures (e.g., "No Fill") are handled gracefully without crashing the app.
