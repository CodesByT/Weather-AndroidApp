package com.example.weatherapp.Screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import com.example.weatherapp.Data.DataOrException
import com.example.weatherapp.Models.Weather
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.Utilitites.formatDate
import com.example.weatherapp.Utilitites.formatDateTime
import com.example.weatherapp.Widgets.TopSearchBar
import com.example.weatherapp.ui.theme.fontFamily1
import com.example.weatherapp.ui.theme.fontFamily2
import com.example.weatherapp.ui.theme.fontFamily3
import com.example.weatherapp.ui.theme.fontFamily5
import java.text.SimpleDateFormat
import java.util.Calendar


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
            value = mainViewModel.getWeatherData(city = "Budapest")
        }
    ).value


    if (weatherData.loading == true) {
        Box(modifier = Modifier.size(20.dp)) {
            CircularProgressIndicator()
        }

    } else if (weatherData.data != null) {

        MainScreenUI(weatherData.data!!, navController = navController)
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenUI(weatherData: Weather, navController: NavController) {

    val imageURl = "https://openweathermap.org/img/wn/${weatherData.list[0].weather[0].icon}.png"

    val temperature = weatherData.list[0].main.temp.toInt()

    val calendar = remember {
        Calendar.getInstance()
    }
    val day = calendar.get(Calendar.DAY_OF_MONTH) // 1-based (e.g., 1 for January 1st)
    val month = calendar.get(Calendar.MONTH) + 1 // 0-based (e.g., 0 for January, so add 1)
    val year = calendar.get(Calendar.YEAR)
    val formattedDay = String.format("%02d", day) // Leading zero for single digits
    val monthName = SimpleDateFormat("MMMM").format(calendar.time)
    val dayName = SimpleDateFormat("EEEE").format(calendar.time)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.n3),
            contentDescription = "HomeScreen",
            contentScale = ContentScale.Crop,
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopSearchBar(
                    title = weatherData.city.name + ", ${weatherData.city.country}",
                    navController = navController,
                    elevation = 5.dp,
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
                    //verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
// City and County
                        fontFamily = fontFamily2,
                        modifier = Modifier.padding(top = 15.dp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(5.0f, 10.0f), blurRadius = 3f
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
                                color = Color.Black, offset = Offset(5.0f, 10.0f), blurRadius = 3f
                            )
                        ),
                        text = formatDate(weatherData.list[0].dt.toInt()),
                        fontSize = 25.sp,
                        color = Color(0xFFCFCDCD),
                    )
                    Text(// Temperature
                        fontFamily = fontFamily5,
                        modifier = Modifier.padding(top = 15.dp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black, offset = Offset(5.0f, 10.0f), blurRadius = 3f
                            )
                        ),
                        text = "$temperature°c",
                        fontSize = 80.sp,
                        color = Color(0xE1FFFFFF),
                        fontWeight = FontWeight.ExtraBold
                    )
                    Divider( // Divider
                        modifier = Modifier.width(150.dp),
                        color = Color(0xE1FFFFFF),
                        thickness = 3.dp
                    )
                    Row(// Row 1
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            fontFamily = fontFamily2,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(5.0f, 10.0f),
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
                    Row(// Humidity and Wind pressure Row
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end =20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(5.0f, 10.0f),
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
                                        offset = Offset(5.0f, 10.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = weatherData.list[0].main.humidity.toString()+ " psi",
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
                                        offset = Offset(5.0f, 10.0f),
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
                                        offset = Offset(5.0f, 10.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = weatherData.list[0].main.pressure.toString()+ " mph",
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                            )
                        }
                    }
                    Row(// Sun Rise and Sun Set
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end =20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(
                                fontFamily = fontFamily2,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(5.0f, 10.0f),
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
                                        offset = Offset(5.0f, 10.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text =  formatDateTime(weatherData.city.sunrise),
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
                                        offset = Offset(5.0f, 10.0f),
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
                                        offset = Offset(5.0f, 10.0f),
                                        blurRadius = 3f
                                    )
                                ),
                                text = formatDateTime(weatherData.city.sunset),
                                fontSize = 15.sp,
                                color = Color(0xE1FFFFFF),
                            )
                        }
                    }


                }

            }

        }
    }
}