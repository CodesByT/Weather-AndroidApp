package com.example.weatherapp.Widgets

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.Models.WeatherItem
import com.example.weatherapp.Utilitites.formatDate2
import com.example.weatherapp.Utilitites.formatDateOnlyDayName
import com.example.weatherapp.Utilitites.formatDateTime
import com.example.weatherapp.ui.theme.fontFamily2
import com.example.weatherapp.ui.theme.fontFamily4
import com.example.weatherapp.ui.theme.fontFamily5

@Composable
fun WeekDayTemperatureTile(data: List<WeatherItem>) {
    val weatherData = data.firstOrNull()
    val imageURl = "https://openweathermap.org/img/wn/${weatherData?.weather?.get(0)?.icon}.png"
    Log.d("T","Icon is $weatherData?.weather?.get(0)?.icon")
    val weatherDayName = weatherData?.dt?.let { formatDateOnlyDayName(it.toInt()) }
    val weatherDate = weatherData?.dt?.let { formatDate2(it.toInt()) }
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xE20F0F0F),
        ),
        border = BorderStroke(1.dp, Color(0xAEFFFFFF)),
        modifier = Modifier
            .width(135.dp)
            .height(IntrinsicSize.Min)
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageURl),
                contentDescription = "Icon",
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = weatherData?.main?.temp?.toInt().toString() + " °c",
                modifier = Modifier
                    .padding(0.dp),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily5,
                fontSize = 27.sp,
                color = Color.White

            )
            Text(
                text = weatherDayName.toString(),
                modifier = Modifier.padding(top = 5.dp),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily2,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = weatherDate.toString(),
                modifier = Modifier.padding(bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily2,
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}
