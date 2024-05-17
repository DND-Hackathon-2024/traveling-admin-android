package com.plass.travlingadmin.ui.feature.create_trap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plass.travlingadmin.remote.RetrofitBuilder
import com.plass.travlingadmin.remote.request.CreateCouponRequest
import com.plass.travlingadmin.remote.request.CreateTrapRequest
import com.plass.travlingadmin.ui.component.TVCTAButton
import com.plass.travlingadmin.ui.component.TVTextField
import com.plass.travlingadmin.ui.component.TVTopAppBar
import com.plass.travlingadmin.ui.feature.root.NavRoot
import com.plass.travlingadmin.ui.theme.TravelingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CreateTrapScreen(
    navController: NavController,
    couponId: Int,
    hideBottomNav: () -> Unit,
) {

    val coroutine = rememberCoroutineScope()

    var placeName by remember { mutableStateOf("") }
    var placeDesc by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        hideBottomNav()
    }

    fun createCoupon() {
        coroutine.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val request = CreateTrapRequest(
                    placeName = placeName,
                    placeDesc = placeDesc,
                    address = address,
                    couponId = couponId,
                    imgUrl = ""
                )
                val response = RetrofitBuilder.getPlaceApi().createTrap(request)
                return@runCatching response
            }.onSuccess {
                coroutine.launch(Dispatchers.Main) {
                    while (navController.popBackStack()) {

                    }
                    navController.navigate(NavRoot.HOME)
                }
            }.onFailure { }
        }
    }

    TVTopAppBar(
        text = "",
        onClickBackButton = {
            navController.popBackStack()
        }
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp),
                text = "트랩 정보를 입력해주세요",
                style = TravelingTheme.typography.headline1B
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = "장소 이름"
                )
                TVTextField(value = placeName, onValueChange = { placeName = it })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = "장소 설명"
                )
                TVTextField(value = placeDesc, onValueChange = { placeDesc = it })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = "장소 주소"
                )
                TVTextField(value = address, onValueChange = { address = it })
            }
            Spacer(modifier = Modifier.weight(1f))
            TVCTAButton(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = "계속하기"
            ) {
                createCoupon()
            }
        }
    }
}