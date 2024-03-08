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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import com.example.weatherapp.Navigation.WeatherScreens
import com.example.weatherapp.R
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
            navController.navigate(WeatherScreens.MainScreen.name)
        }
    )

    Surface(
        modifier = Modifier
            .scale(scale.value)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashscreen2),
            contentDescription = "Splash Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun WeatherSplashScreenPreview() {
    val navController = rememberNavController()
    WeatherSplashScreen(navController)
}