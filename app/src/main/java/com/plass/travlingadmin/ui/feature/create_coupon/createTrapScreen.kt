package com.plass.travlingadmin.ui.feature.create_coupon

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
fun CreateCouponScreen(
    navController: NavController,
    hideBottomNav: () -> Unit
) {

    val coroutine = rememberCoroutineScope()

    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        hideBottomNav()
    }

    fun createCoupon() {
        coroutine.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val request = CreateCouponRequest(
                    couponName = "",
                    description = description,
                    location = location,
                    couponDiscount = discount
                )
                val response = RetrofitBuilder.getCouponApi().createCoupon(request).data
                return@runCatching response
            }.onSuccess {
                coroutine.launch(Dispatchers.Main) {
                    navController.navigate("${NavRoot.CREATE_TRAP}/${it.couponId}")
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
                text = "먼저 쿠폰을 등록해야 해요",
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
                    text = "쿠폰 생성 위치"
                )
                TVTextField(value = location, onValueChange = { location = it })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = "쿠폰 설명"
                )
                TVTextField(value = description, onValueChange = { description = it })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    text = "할인율"
                )
                TVTextField(value = discount, onValueChange = { discount = it })
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