package com.plass.travlingadmin.ui.feature.locate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plass.travlingadmin.ui.component.travelingHorizontalGradient
import com.plass.travlingadmin.ui.theme.TravelingTheme

@Composable
fun LocateScreen(
    navController: NavController
) {
    val testItems = listOf(
        LocateModel("대구소프트웨어 체육관 어딘가", 200, 5, "https://i.namu.wiki/i/7ivconN-POEZUVREF3kHCQo5C7Dhfc_KlSX6BbvJm4TXL8QQ-R5p-2AEvXV_gHZ4nFzzeGIx7ZbDyvMaOvu9dA.webp"),
        LocateModel("대구소프트웨어 체육관 어딘가", 200, 5, "https://i.namu.wiki/i/7ivconN-POEZUVREF3kHCQo5C7Dhfc_KlSX6BbvJm4TXL8QQ-R5p-2AEvXV_gHZ4nFzzeGIx7ZbDyvMaOvu9dA.webp"),
    )

    val testItems2 = listOf(
        LocateModel("종로고택점 스타벅스 남자화장실 어딘가", 10, 2, "https://i.namu.wiki/i/7ivconN-POEZUVREF3kHCQo5C7Dhfc_KlSX6BbvJm4TXL8QQ-R5p-2AEvXV_gHZ4nFzzeGIx7ZbDyvMaOvu9dA.webp"),
        LocateModel("대구소프트웨어 체육관 어딘가", 4, 7, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq6-AUQPxjwV1A05pE1tdBt_yv8HKPB2TogFeat0pEOw&s"),
        LocateModel("종로고택점 스타벅스 남자화장실 어딘가", 10, 2, "https://i.namu.wiki/i/7ivconN-POEZUVREF3kHCQo5C7Dhfc_KlSX6BbvJm4TXL8QQ-R5p-2AEvXV_gHZ4nFzzeGIx7ZbDyvMaOvu9dA.webp"),
        LocateModel("대구소프트웨어 체육관 어딘가", 4, 7, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq6-AUQPxjwV1A05pE1tdBt_yv8HKPB2TogFeat0pEOw&s"),
        LocateModel("종로고택점 스타벅스 남자화장실 어딘가", 10, 2, "https://i.namu.wiki/i/7ivconN-POEZUVREF3kHCQo5C7Dhfc_KlSX6BbvJm4TXL8QQ-R5p-2AEvXV_gHZ4nFzzeGIx7ZbDyvMaOvu9dA.webp"),
        LocateModel("대구소프트웨어 체육관 어딘가", 4, 7, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq6-AUQPxjwV1A05pE1tdBt_yv8HKPB2TogFeat0pEOw&s"),
        LocateModel("종로고택점 스타벅스 남자화장실 어딘가", 10, 2, "https://i.namu.wiki/i/7ivconN-POEZUVREF3kHCQo5C7Dhfc_KlSX6BbvJm4TXL8QQ-R5p-2AEvXV_gHZ4nFzzeGIx7ZbDyvMaOvu9dA.webp"),
        LocateModel("대구소프트웨어 체육관 어딘가", 4, 7, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq6-AUQPxjwV1A05pE1tdBt_yv8HKPB2TogFeat0pEOw&s"),
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TravelingTheme.colorScheme.White)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "서울 강남 ",
                style = TravelingTheme.typography.title1B.copy(
                    brush = travelingHorizontalGradient()
                ),
            )
            Text(
                text = "주변",
                style = TravelingTheme.typography.title1B,
                color = TravelingTheme.colorScheme.Black
            )
        }
        LazyColumn {
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    ),
                    text = "가장 가까이 있어요",
                    style = TravelingTheme.typography.headline2B,
                    color = TravelingTheme.colorScheme.Black
                )
            }
            items(testItems) {
                LocateItem(
                    locate = it.locate,
                    image = it.image,
                    distance = it.distance,
                    like = it.like
                ) {

                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color(0xFFF4F5F9)
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    ),
                    text = " 주변 NFC",
                    style = TravelingTheme.typography.headline2B,
                    color = TravelingTheme.colorScheme.Black
                )
            }
            items(testItems2) {
                LocateItem(
                    locate = it.locate,
                    image = it.image,
                    distance = it.distance,
                    like = it.like
                ) {

                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color(0xFFF4F5F9)
                )
            }
        }

    }
}

data class LocateModel(
    val locate: String,
    val distance: Int,
    val like: Int,
    val image: String
)