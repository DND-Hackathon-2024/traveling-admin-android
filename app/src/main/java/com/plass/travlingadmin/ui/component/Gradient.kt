package com.plass.travlingadmin.ui.component

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun travelingVerticalGradient() =
    Brush.verticalGradient(
        0.0f to Color(0xFF0774FA),
        1.0f to Color(0xFF4F3FFF),
        startY = 0f,
        endY = 1f
    )

fun travelingHorizontalGradient() =
    Brush.horizontalGradient(
        0.0f to Color(0xFF0774FA),
        1.0f to Color(0xFF4F3FFF),
        startX = 0f,
        endX = 1f
    )