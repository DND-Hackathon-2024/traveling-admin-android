package com.plass.travling.ui.feature.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plass.travling.ui.component.TVTopAppBar
import com.plass.travling.ui.component.bounceClick
import com.plass.travling.ui.theme.TravelingTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
    var location by remember {
        mutableStateOf("전체")
    }
    TVTopAppBar(
        text = "홈",
        backgroundColor = Color(0xFFF4F5F9)
    ) {
        LazyColumn {
            item {

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "사용가능한 쿠폰이", style = TravelingTheme.typography.headline2B)
                    Text(text = "3개 남아있어요", style = TravelingTheme.typography.headline2B)
                }
            }
            item {
                LazyRow(
                    modifier = Modifier
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(arrayListOf("전체", "대구", "부산", "서울", "광주", "인천", "공주")) {
                        LocationCell(text = it, selected = it == location) {
                            location = it
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun LocationCell(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val w = if (selected) 1.5.dp else 1.dp
    val c = if (selected) TravelingTheme.colorScheme.Blue else Color(0xFFE3E3E3)
    val tc = if (selected) TravelingTheme.colorScheme.Blue else Color(0xFF646464)
    Box(
        modifier = Modifier
            .bounceClick(onClick = onClick)
            .border(width = w, color = c, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = TravelingTheme.typography.labelMedium,
            color = tc
        )
    }
}