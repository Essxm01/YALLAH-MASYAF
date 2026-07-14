package com.yallahmasyaf.app.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DeepOceanBlue,
    secondary = CoastalGold,
    tertiary = SunsetCoral,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = TextMainDark,
    onSurface = TextMainDark
)

private val LightColorScheme = lightColorScheme(
    primary = DeepOceanBlue,
    secondary = CoastalGold,
    tertiary = SunsetCoral,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = TextMainLight,
    onSurface = TextMainLight
)

@Composable
fun YallahMasyafTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
