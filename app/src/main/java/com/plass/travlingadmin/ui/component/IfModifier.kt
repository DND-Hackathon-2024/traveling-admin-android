package com.plass.travlingadmin.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.`if`(
    enabled: Boolean,
    modifier: @Composable Modifier.() -> Modifier
) = composed { if (enabled) modifier() else this }