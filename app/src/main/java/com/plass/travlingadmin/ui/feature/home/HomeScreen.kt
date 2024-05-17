package com.plass.travlingadmin.ui.feature.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plass.travlingadmin.remote.RetrofitBuilder
import com.plass.travlingadmin.remote.response.TrapResponse
import com.plass.travlingadmin.ui.component.TVTopAppBar
import com.plass.travlingadmin.ui.feature.locate.LocateItem
import com.plass.travlingadmin.ui.theme.TravelingTheme
import com.plass.travlingadmin.utiles.TAG

@Composable
fun HomeScreen(
    navController: NavController,
    showBottomNav: () -> Unit
) {

    var placeList by remember {
        mutableStateOf<List<TrapResponse>>(arrayListOf())
    }

    LaunchedEffect(Unit) {
        showBottomNav()
        kotlin.runCatching {
            return@runCatching RetrofitBuilder.getPlaceApi().getTraps().data
        }.onSuccess {
            placeList = it
        }.onFailure {
            Log.d(TAG, "HomeScreen: $it")
        }
    }

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
            items(placeList) {
                LocateItem(locate = it.placeName, image = it.imgUrl, distance = 3, like = 3) {

                }
            }
        }
    }
}
