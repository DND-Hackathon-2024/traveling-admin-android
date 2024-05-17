package com.plass.travling.ui.feature.root

import androidx.annotation.DrawableRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plass.travling.R
import com.plass.travling.ui.component.DropShadowType
import com.plass.travling.ui.component.dropShadow
import com.plass.travling.ui.component.`if`
import com.plass.travling.ui.component.rippleClickable
import com.plass.travling.ui.theme.TravelingColor
import com.plass.travling.ui.theme.TravelingTheme

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    onClickItem: (BottomNavItem) -> Unit,
    onClickNfc: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .dropShadow(DropShadowType.Nav)
            .background(
                color = TravelingColor.White,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            )
    ) {
        Spacer(modifier = Modifier.width(24.dp))
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                if (item.title == "nfc"){
                    Box(
                        modifier = Modifier
                            .offset(y = (-12).dp)
                            .size(48.dp)
                            .background(
                                color = TravelingTheme.colorScheme.Blue,
                                shape = RoundedCornerShape(49.dp)
                            )
                    ) {
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center)
                                .rippleClickable(
                                    onClick = onClickNfc,
                                ),
                            painter = painterResource(id = item.resId),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(TravelingColor.White)
                        )
                    }
                }
                else {
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .rippleClickable(
                                enabled = !item.isSelected
                            ) {
                                onClickItem(item)
                            },
                        painter = painterResource(id = item.resId),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(if (item.isSelected) TravelingColor.BlackBrown else TravelingColor.Gray)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
    }
}


data class BottomNavItem(
    val title: String,
    val isSelected: Boolean,
    @DrawableRes val resId: Int
)