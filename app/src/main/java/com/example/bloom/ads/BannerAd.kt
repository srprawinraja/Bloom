package com.example.bloom.ads

import android.util.DisplayMetrics
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(
    modifier: Modifier = Modifier,
    adUnitId: String = AdsConfig.getBannerAdUnitId()
) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { ctx ->
            AdView(ctx).apply {
                this.adUnitId = adUnitId
                val adSize = getAdaptiveAdSize(ctx)
                setAdSize(adSize)
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

private fun getAdaptiveAdSize(context: android.content.Context): AdSize {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val widthPixels = displayMetrics.widthPixels
    val density = displayMetrics.density
    val adWidth = (widthPixels / density).toInt()
    // Current orientation anchored adaptive banner
    return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
}
