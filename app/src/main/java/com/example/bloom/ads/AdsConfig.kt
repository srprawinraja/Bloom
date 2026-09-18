package com.example.bloom.ads

object AdsConfig {
    /**
     * AdMob App ID: ca-app-pub-7730240440497136~5960685871
     * 
     * FOR TESTING (Use this in AndroidManifest.xml if production App ID fails):
     * ca-app-pub-3940256099942544~3347511713
     */
    
    // Production IDs
    private const val APP_OPEN_AD_UNIT_ID = "ca-app-pub-7730240440497136/3177600706"
    private const val BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"

    // Standard SDK App Open Test ID
    private const val TEST_APP_OPEN_AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921"
    private const val TEST_BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"

    // Set this to true for development!
    private var isTestMode = true

    fun getAppOpenAdUnitId(): String {
        return if (isTestMode) TEST_APP_OPEN_AD_UNIT_ID else APP_OPEN_AD_UNIT_ID
    }

    fun getBannerAdUnitId(): String {
        return if (isTestMode) TEST_BANNER_AD_UNIT_ID else BANNER_AD_UNIT_ID
    }

    fun setTestMode(enabled: Boolean) {
        isTestMode = enabled
    }
}
