package com.plass.travlingadmin.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Composable
fun TravelingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color(0x00000000).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    CompositionLocalProvider(
        LocalTravelingColor provides TravelingTheme.colorScheme,
        LocalTravelingTypography provides TravelingTheme.typography,
        content = content
    )
}


object TravelingTheme {
    val colorScheme: TravelingColor
        @Composable
        @ReadOnlyComposable
        get() = LocalTravelingColor.current

    val typography: TravelingTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTravelingTypography.current
}