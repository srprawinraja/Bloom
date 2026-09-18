package com.example.bloom.ads

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.bloom.data.UserPreferences
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.Date

private const val TAG = "AppOpenAdManager"

/**
 * Manages the loading and showing of App Open Ads.
 */
class AppOpenAdManager(private val application: Application) : DefaultLifecycleObserver, Application.ActivityLifecycleCallbacks {

    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    private var isShowingAd = false
    private var loadTime: Long = 0
    private var currentActivity: Activity? = null
    private val userPreferences = UserPreferences(application)
    
    private var showAdOnNextLoad = false

    init {
        Log.d(TAG, "0. AppOpenAdManager initializing.")
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    /** Request an ad. */
    fun loadAd(context: Context, showOnLoad: Boolean = false) {
        if (showOnLoad) {
            showAdOnNextLoad = true
            Log.d(TAG, "2. App Open load requested with showOnLoad=true.")
        } else {
            Log.d(TAG, "2. App Open preload started.")
        }

        if (isLoadingAd) {
            Log.d(TAG, "2. App Open load skipped: Already loading.")
            return
        }

        if (isAdAvailableInternal()) {
            Log.d(TAG, "2. App Open load skipped: Ad already available.")
            return
        }

        isLoadingAd = true
        val adUnitId = AdsConfig.getAppOpenAdUnitId()
        Log.d(TAG, "2. App Open load started. Ad Unit ID: $adUnitId")
        
        val request = AdRequest.Builder().build()
        AppOpenAd.load(
            application, 
            adUnitId,
            request,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = Date().time
                    Log.d(TAG, "3. App Open load succeeded.")
                    
                    if (showAdOnNextLoad) {
                        Log.d(TAG, "7. Attempting to show App Open (auto-show after load).")
                        showAdOnNextLoad = false
                        currentActivity?.let {
                            showAdIfAvailable(it)
                        } ?: Log.w(TAG, "7. Cannot show App Open: No valid activity available.")
                    }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoadingAd = false
                    showAdOnNextLoad = false
                    Log.e(TAG, "4. App Open load failed. Code: ${loadAdError.code}, Message: ${loadAdError.message}")
                    Log.e(TAG, "   Domain: ${loadAdError.domain}")
                    Log.e(TAG, "   ResponseInfo: ${loadAdError.responseInfo}")
                }
            }
        )
    }

    /** Check if ad exists and can be shown. Internal version without extra logging. */
    private fun isAdAvailableInternal(): Boolean {
        return appOpenAd != null && (Date().time - loadTime) < 3600000 * 4
    }

    /** Shows the ad if one isn't already showing and onboarding is done. */
    fun showAdIfAvailable(activity: Activity) {
        val onboardingCompleted = userPreferences.isOnboardingCompleted
        val isAvailable = isAdAvailableInternal()
        
        Log.d(TAG, "6. App Open availability check: Available=$isAvailable, Onboarded=$onboardingCompleted, isShowingAd=$isShowingAd")
        
        if (isShowingAd) {
            Log.d(TAG, "7. Skipping show: Ad already showing.")
            return
        }

        if (!onboardingCompleted) {
            Log.d(TAG, "7. Skipping show: Onboarding not completed.")
            return
        }

        if (!isAvailable) {
            Log.d(TAG, "7. Ad not available. Triggering load with showOnLoad=true.")
            loadAd(activity, showOnLoad = true)
            return
        }

        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "10. App Open dismissed.")
                appOpenAd = null
                isShowingAd = false
                Log.d(TAG, "11. Next App Open preload started.")
                loadAd(application)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.e(TAG, "9. App Open failed to show. Message: ${adError.message}")
                appOpenAd = null
                isShowingAd = false
                Log.d(TAG, "11. Next App Open preload started (after show failure).")
                loadAd(application)
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "8. App Open shown.")
                isShowingAd = true
            }
        }
        
        Log.d(TAG, "7. Executing show() on activity: ${activity.javaClass.simpleName}")
        appOpenAd?.show(activity)
    }

    /** ProcessLifecycleObserver method. Called when app moves to foreground. */
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d(TAG, "5. App enters foreground.")
        
        val activity = currentActivity
        if (activity != null) {
            showAdIfAvailable(activity)
        } else {
            Log.w(TAG, "5. No valid activity available to show ad.")
        }
    }

    /** ActivityLifecycleCallbacks to track the current active Activity. */
    override fun onActivityStarted(activity: Activity) {
        Log.d(TAG, "Activity Started: ${activity.javaClass.simpleName}")
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        Log.d(TAG, "Activity Resumed: ${activity.javaClass.simpleName}")
        currentActivity = activity
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        if (currentActivity == activity) {
            currentActivity = null
        }
    }
}
