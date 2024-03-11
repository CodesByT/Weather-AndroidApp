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
