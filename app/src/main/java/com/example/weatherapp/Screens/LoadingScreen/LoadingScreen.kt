package com.example.weatherapp.Screens.LoadingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.fontFamily1
import com.example.weatherapp.ui.theme.fontFamily2
import com.example.weatherapp.ui.theme.fontFamily3
import com.example.weatherapp.ui.theme.fontFamily4
import com.example.weatherapp.ui.theme.fontFamily5

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.a8),
            contentDescription = "Splash Image",
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.padding(top = 10.dp, bottom = 170.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(id = R.drawable.l2),
                contentDescription = ""
            )
            Box(modifier = Modifier.size(60.dp)) {
                CircularProgressIndicator(
                    color = Color.Black
                )
            }
        }
    }
}