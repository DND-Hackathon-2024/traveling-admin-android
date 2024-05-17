package com.plass.travling.ui.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plass.travling.ui.component.TVTopAppBar
import com.plass.travling.ui.feature.locate.LocateItem
import com.plass.travling.ui.theme.TravelingTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
    TVTopAppBar(
        text = "홈",
        backgroundColor = Color(0xFFF4F5F9)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .padding(vertical = 4.dp),
                    text = "등록된 트랩",
                    style = TravelingTheme.typography.headline2B
                )
            }
            items(arrayListOf("")) {
                LocateItem(locate = "대구소프트고등학교 체육관 어딘가", image = "", distance = 1, like = 10) {}
            }
        }
    }
}
