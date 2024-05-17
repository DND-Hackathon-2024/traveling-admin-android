package com.plass.travling.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    onClick: () -> Unit,
) = composed { this.clickable(
    onClick = onClick,
    enabled = enabled,
    interactionSource = remember { MutableInteractionSource() },
    indication = rememberRipple()
) }