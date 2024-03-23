package com.example.weatherapp.Screens.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.example.weatherapp.Data.DataOrException
import com.example.weatherapp.Models.Weather
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.Data.ImageProvider
import com.example.weatherapp.Navigation.WeatherScreens
import com.example.weatherapp.Screens.LoadingScreen.LoadingScreen
import com.example.weatherapp.Utilitites.formatDate
import com.example.weatherapp.Utilitites.formatDateTime
import com.example.weatherapp.Widgets.TopSearchBar
import com.example.weatherapp.Widgets.WeekDayTemperatureTile
import com.example.weatherapp.ui.theme.fontFamily2
import com.example.weatherapp.ui.theme.fontFamily5
import kotlinx.coroutines.launch
import kotlin.random.Random


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    city: String?
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
        producer = {
            value = mainViewModel.getWeatherData(city = city!!)
        }
    ).value

    val defaultCityWeatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
        producer = {
            value = mainViewModel.getWeatherData(city = "Lahore")
        }
    ).value

    if (weatherData.loading == true) {
        LoadingScreen()

    } else if (weatherData.data != null && weatherData.data!!.cod.toString() == "200") {
        MainScreenUI(weatherData.data!!, navController = navController, WrongInput = false)
    } else if(weatherData.data!!.cod.toString() != "200" ){
        // In SOME case when user input Wrong City
        MainScreenUI(defaultCityWeatherData.data!!, navController = navController, WrongInput = true)
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenUI(weatherData: Weather, navController: NavController, WrongInput: Boolean) {

    val imageURl = "https://openweathermap.org/img/wn/${weatherData.list[0].weather[0].icon}.png"

    val temperature = weatherData.list[0].main.temp.toInt()

    // Background Image Animation Instructions
    //----------------------------------------------------------------------

    var currentImageIndex by remember { mutableStateOf(Random.nextInt(ImageProvider.images.size)) }
    val coroutineScope = rememberCoroutineScope()
    // Coroutine to switch image every 10 seconds
    DisposableEffect(Unit) {
        // Launch the coroutine within the provided coroutine scope
        val job = coroutineScope.launch {
            while (true) {
                delay(20000)
                currentImageIndex = (currentImageIndex + Random.nextInt(ImageProvider.images.size)) % ImageProvider.images.size
            }
        }
        // When the composable is disposed, cancel the coroutine job
        onDispose {
            job.cancel()
        }
    }
    val currentImage = remember(currentImageIndex) {
        // This image provider is an object containing list of images
        ImageProvider.images[currentImageIndex]
    }
    //----------------------------------------------------------------------
    if(WrongInput){
        Toast.makeText(LocalContext.current,"WRONG CITY INPUT ⚠️",Toast.LENGTH_SHORT).show()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        // Crossfade is a composable function provided by Jetpack Compose for animating the transition between two states of a UI element.
        Crossfade(
            targetState = currentImage,
            animationSpec = tween(2000), label = ""
        ) { targetImage->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = targetImage),
                contentDescription = "HomeScreen",
                contentScale = ContentScale.Crop,
            )
        }

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopSearchBar(
                    navController = navController,
                    elevation = 5.dp,
                    onAddActionClicked = {
                        navController.navigate(WeatherScreens.SearchScreen.name)
                    }
                )
            }
        ) {
            Surface(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
                color = Color.Transparent
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Space
                ) {
                    Text(
                        // City and County
                        fontFamily = fontFamily2,
                        modifier = Modifier.padding(top = 15.dp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(4.0f, 8.0f), blurRadius = 3f
                            )
                        ),
                        text = weatherData.city.name + ", ${weatherData.city.country}",
                        fontSize = 40.sp,
                        color = Color(0xE1FFFFFF),
                    )
                    Text(
                        // Date
                        fontFamily = fontFamily2,
                        modifier = Modifier.padding(top = 10.dp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(3.0f, 6.0f), blurRadius = 3f
                            )
                        ),
                        text = formatDate(weatherData.list[0].dt.toInt()),
                        fontSize = 25.sp,
                        color = Color(0xFFCFCDCD),
                    )
                    Text(
                        // Temperature
                        fontFamily = fontFamily5,
                        modifier = Modifier.padding(top = 15.dp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(5.0f, 10.0f), blurRadius = 3f
                            )
                        ),
                        text = "${weatherData.list[0].main.temp.toInt()}°c",
                        fontSize = 80.sp,
                        color = Color(0xE1FFFFFF),
                        fontWeight = FontWeight.ExtraBold
                    )
                    Divider(
                        // Divider
                        modifier = Modifier.width(150.dp),
                        color = Color(0xE1FFFFFF),
                        thickness = 3.dp
                    )
                    Row(
                        // Weather Condition
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            fontFamily = fontFamily2,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2.0f, 4.0f),
                                    blurRadius = 3f
                                )
                            ),
                            text = weatherData.list[0].weather[0].main,
                            fontSize = 25.sp,
                            color = Color(0xE1FFFFFF),
                        )
                        Image(
                            painter = rememberAsyncImagePainter(imageURl),
                            contentDescription = "Icon",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Row(
                        // Humidity and Wind pressure Row
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = "Humidity Index: ",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                                fontWeight = FontWeight.W700
                            )
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = weatherData.list[0].main.humidity.toString() + " psi",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                            )
                        }
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = "Wind Pressure: ",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                                fontWeight = FontWeight.W700
                            )
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = weatherData.list[0].main.pressure.toString() + " mph",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                            )
                        }
                    }
                    Row(
                        // Sun Rise and Sun Set
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 40.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = "Sun Rise: ",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                                fontWeight = FontWeight.W700
                            )
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = formatDateTime(weatherData.city.sunrise),
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                            )
                        }
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = "Sun Set: ",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                                fontWeight = FontWeight.W700
                            )
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2.0f, 4.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = formatDateTime(weatherData.city.sunset),
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.padding(top = 50.dp),
                        text = "Weekly Report",
                        fontSize = 22.sp,
                        fontFamily = fontFamily2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2.0f, 4.0f),
                                blurRadius = 3f
                            )
                        ),
                    )
                    WeeklyReportRow(weatherData)

                }

            }

        }
    }
}

@Composable
fun WeeklyReportRow(data: Weather) {
    Surface(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        color = Color.Transparent
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            contentPadding = PaddingValues(5.dp),

            ) {
            val DaysData = data.list.chunked(8)
            items(DaysData) { dayData ->

                val temperatureOfDay = dayData.firstOrNull()?.main?.temp_max.toString()
                val temperatureDate = dayData.firstOrNull()?.dt_txt.toString()

                WeekDayTemperatureTile(dayData)

            }

        }
    }
}

