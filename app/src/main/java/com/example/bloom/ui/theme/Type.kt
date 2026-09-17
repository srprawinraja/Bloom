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
    Font(R.font.plusjakarta_sans_regular, FontWeight.Normal)
)

val BloomTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Fraunces,
        fontWeight = FontWeight(600),
        fontStyle = FontStyle.Italic,
        fontSize = 30.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)