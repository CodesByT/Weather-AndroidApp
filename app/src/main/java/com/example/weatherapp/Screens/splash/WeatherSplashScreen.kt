package com.example.weatherapp.Screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.Navigation.WeatherScreens
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.fontFamily1
import com.example.weatherapp.ui.theme.fontFamily2
import com.example.weatherapp.ui.theme.fontFamily3
import com.example.weatherapp.ui.theme.fontFamily4
import com.example.weatherapp.ui.theme.fontFamily5
import kotlinx.coroutines.delay


@Composable
fun WeatherSplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 0.9f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(8f).getInterpolation(it)
                    }
                )
            )
            delay(4000L)
            val defaultCity = "Lahore"
            navController.navigate(WeatherScreens.MainScreen.name+"/$defaultCity")
        }
    )

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
            modifier  = Modifier.padding(top = 10.dp, bottom = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(id = R.drawable.l2),
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(scale.value),
                contentAlignment = Alignment.Center
            ) {


                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Your personal weather concierge.....",
                    fontFamily = fontFamily4,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF000000),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,


                )
            }

            // This is just a DESI technique to load the fonts when we are on the splash screen
            Text(text = "", fontFamily = fontFamily5)
            Text(text = "", fontFamily = fontFamily3)
            Text(text = "", fontFamily = fontFamily2)
            Text(text = "", fontFamily = fontFamily1)

        }
    }
}

@Preview
@Composable
fun WeatherSplashScreenPreview() {
    val navController = rememberNavController()
    WeatherSplashScreen(navController)
}