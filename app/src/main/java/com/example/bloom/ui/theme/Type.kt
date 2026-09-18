package com.example.bloom.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bloom.R

val Fraunces = FontFamily(
    Font(R.font.fraunces_72pt_soft_italic, FontWeight.SemiBold, FontStyle.Italic),
)

val PlusJakartaSans = FontFamily(
    Font(R.font.plusjakarta_sans_regular, FontWeight.Normal),
)

// Type.kt

val BloomTypography = Typography(
    // Large Brand Headers (Fraunces)
    headlineLarge = TextStyle(
        fontFamily = Fraunces,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        lineHeight = 30.sp
    ),

    // Medium Brand Headers (Fraunces)
    headlineMedium = TextStyle(
        fontFamily = Fraunces,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        lineHeight = 22.sp
    ),

    // Section Titles (Plus Jakarta Bold)
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 28.sp
    ),

    // Card/Component Titles (Plus Jakarta SemiBold)
    titleMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    ),

    // Normal Body Text
    bodyMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.4.sp
    ),

    // Small Metadata/Description
    bodySmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp
    ),

    // Buttons and Primary Labels
    labelLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 15.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 15.sp
    ),

    // Small caps/Overline (e.g. "GOOD MORNING")
    labelSmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.5.sp,
        lineHeight = 16.sp
    )
)
