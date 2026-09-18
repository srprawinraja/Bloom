package com.example.bloom

import android.app.Application
import android.util.Log
import com.example.bloom.ads.AppOpenAdManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class BloomApplication : Application() {

    private lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()
        
        Log.d("BloomApplication", "Application onCreate started.")
        
        // Initialize App Open Ad Manager first so it can register callbacks
        appOpenAdManager = AppOpenAdManager(this)

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this) { status ->
            Log.d("BloomApplication", "1. Mobile Ads SDK initialization complete. Status: ${status.adapterStatusMap}")
            
            // Set test device configuration for development
            val testDeviceIds = listOf(AdRequest.DEVICE_ID_EMULATOR)
            val configuration = RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds)
                .build()
            MobileAds.setRequestConfiguration(configuration)

            // Preload the first ad
            appOpenAdManager.loadAd(this)
        }
    }
}
