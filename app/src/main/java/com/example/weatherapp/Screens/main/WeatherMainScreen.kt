package com.example.weatherapp.Screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import com.example.weatherapp.Data.DataOrException
import com.example.weatherapp.Models.Weather
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.Widgets.TopSearchBar
import com.example.weatherapp.ui.theme.fontFamily1


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
        producer = {
            value = mainViewModel.getWeatherData(city = "Lahore")
        }
    ).value


    if (weatherData.loading == true) {
        Box(modifier = Modifier.size(20.dp)) {
            CircularProgressIndicator()
        }

    } else if (weatherData.data != null) {

        MainScreenUI(weatherData.data!!,navController = navController)
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenUI(weatherData: Weather,navController: NavController,) {

    val temperature = weatherData.list[0].main.temp.toInt()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.n12),
            contentDescription = "HomeScreen",
            contentScale = ContentScale.Crop,
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopSearchBar(title = "Lahore",navController = navController)
            }
        ) {
            Surface (
                modifier = Modifier.padding(top= it.calculateTopPadding()),
                color = Color.Transparent
            ){
                Text(
                    fontFamily = fontFamily1,
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "$temperature°c",
                    fontSize = 60.sp,
                    color = Color.White
                )
            }

        }
    }
}