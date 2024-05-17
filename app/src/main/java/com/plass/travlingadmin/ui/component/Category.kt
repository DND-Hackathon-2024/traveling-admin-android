package com.plass.travlingadmin.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plass.travlingadmin.ui.theme.TravelingTheme

@Composable
fun Category(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    Text(
        modifier = modifier
            .background(TravelingTheme.colorScheme.White)
            .border(
                border = BorderStroke(1.dp, Color(0xFFE3E3E3)),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(
                horizontal = 6.dp,
                vertical = 4.dp
            )
            .bounceClick(
                enabled = enabled,
                onClick = onClick
            ),
        text = text,
        color = TravelingTheme.colorScheme.Black64,
        style = TravelingTheme.typography.captionMedium
    )
}