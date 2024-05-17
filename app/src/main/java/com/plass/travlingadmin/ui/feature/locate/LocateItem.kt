package com.plass.travlingadmin.ui.feature.locate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.plass.travlingadmin.R
import com.plass.travlingadmin.remote.response.CouponResponse
import com.plass.travlingadmin.ui.component.Coupon
import com.plass.travlingadmin.ui.component.rippleClickable
import com.plass.travlingadmin.ui.theme.TravelingTheme

@Composable
fun LocateItem(
    locate: String,
    image: String,
    distance: Int,
    like: Int,
    onClickItem: () -> Unit
) {
    Box(
        modifier = Modifier
            .rippleClickable(onClick = onClickItem)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                AsyncImage(
                    model = image,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = locate,
                        style = TravelingTheme.typography.bodyBold,
                        color = TravelingTheme.colorScheme.Black
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = distance.toString() + "km 이내",
                        style = TravelingTheme.typography.labelRegular,
                        color = TravelingTheme.colorScheme.Gray6
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_locate),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(TravelingTheme.colorScheme.Gray98)
                )
                Text(
                    text = (if (like > 99) "99+" else like).toString(),
                    style = TravelingTheme.typography.labelRegular,
                    color = TravelingTheme.colorScheme.Gray98
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}