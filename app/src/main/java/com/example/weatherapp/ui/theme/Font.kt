package com.example.weatherapp.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.weatherapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName1 = GoogleFont("Alfa Slab One")
val fontFamily1 = FontFamily(
    Font(googleFont = fontName1, fontProvider = provider)
)

val fontName2 = GoogleFont("Jost")
val fontFamily2 = FontFamily(
    Font(googleFont = fontName2, fontProvider = provider)
)

val fontName3 = GoogleFont("Indie Flower")
val fontFamily3 = FontFamily(
    Font(googleFont = fontName3, fontProvider = provider)
)

val fontName4 = GoogleFont("Gloria Hallelujah")
val fontFamily4 = FontFamily(
    Font(googleFont = fontName4, fontProvider = provider)
)

val fontName5 = GoogleFont("Staatliches")
val fontFamily5 = FontFamily(
    Font(googleFont = fontName5, fontProvider = provider)
)



