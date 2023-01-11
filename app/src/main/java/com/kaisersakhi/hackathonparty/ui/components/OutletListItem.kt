package com.kaisersakhi.hackathonparty.ui.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kaisersakhi.hackathonparty.R
import com.kaisersakhi.hackathonparty.data.models.BusinessOutlet
import com.kaisersakhi.hackathonparty.ui.theme.Purple700


@Composable
fun OutletListItem(businessOutlet: BusinessOutlet) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(.96f),
        elevation = 10.dp,
        shape = RoundedCornerShape(CornerSize(15.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = businessOutlet.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = businessOutlet.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Rating : ${businessOutlet.rating}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Distance : ${String.format("%.2f", businessOutlet.distance / 1000)} KM",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Address : ${businessOutlet.location.address}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Box(
                    modifier = Modifier.fillMaxWidth(.90f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painterResource(id = R.drawable.get_direction_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                businessOutlet.longitudeLatitude.split(",")
                                val url =
                                    "https://maps.google.com/maps?q=loc:${businessOutlet.longitudeLatitude}"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                Log.d("OutletListItem", "OutletListItem: $url")
                                context.startActivity(intent)
                            }
                            .size(30.dp),
                        tint = Purple700
                    )
                }
            }
        }
    }
}