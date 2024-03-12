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
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.Navigation.WeatherScreens
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.fontFamily1
import com.example.weatherapp.ui.theme.fontFamily4
import kotlinx.coroutines.delay


@Composable
fun WeatherSplashScreen(navController: NavHostController) {
    val gradientColors = listOf(Color(0xffffffff), Color(0xFFFFD54F), Color(0xFFE65100))
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
            navController.navigate(WeatherScreens.MainScreen.name)
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashscreen2),
            contentDescription = "Splash Image",
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = "Your personal weather concierge.",
                fontFamily = fontFamily4,
                textAlign = TextAlign.Center,
                color = Color(0xffffffff),
                fontSize = 20.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )

            )

        }
    }
}

@Preview
@Composable
fun WeatherSplashScreenPreview() {
    val navController = rememberNavController()
    WeatherSplashScreen(navController)
}