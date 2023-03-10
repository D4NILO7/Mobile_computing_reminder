package com.example.mobilecomputingexerciseproject.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.Black

private val DarkColorPalette = darkColors(
        primary = Color(0xFF00C6CF),
        primaryVariant = Color(0xFF00C6CF),
        secondary = Color(0xFF00C6CF),
        background = Color.Black,
)

private val LightColorPalette = lightColors(
        primary = Color(0xFF00C6CF),
        primaryVariant = Color(0xFF00C6CF),
        secondary = Teal200 ,
        /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MobileComputingExerciseProjectTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}