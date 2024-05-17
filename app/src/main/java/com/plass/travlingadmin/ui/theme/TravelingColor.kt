package com.plass.travlingadmin.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

object TravelingColor {
    val Black = Color(0xFF000000)
    val White = Color(0xFFFFFFFF)
    val Blue = Color(0xFF0078F9)
    val Gray = Color(0xFFCCCCD6)
    val Gray6 = Color(0xFF666666)
    val Gray98 = Color(0xFF989898)
    val BlackBrown = Color(0xFF5B5B5B)
    val Black57 = Color(0xFF575757)
    val Transparent = Color(0x00000000)
}

val LocalTravelingColor = compositionLocalOf { TravelingColor }