package com.example.weatherapp.Screens.SearchScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.Navigation.WeatherScreens
import com.example.weatherapp.Widgets.TopSearchBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopSearchBar(
                navController = navController,
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                title = "          ⛅ WeatherScope ⛅"
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {
            Column {
                var cityName: String by remember { mutableStateOf("") }
                var city by rememberSaveable {
                    mutableStateOf("")
                }
                val keyboardController = LocalSoftwareKeyboardController.current
                val valid = remember(cityName) {
                    cityName.isNotEmpty()
                }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    value = cityName,
                    onValueChange = { cityName = it },
                    label = { Text("City name...") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions {

                        if (!valid) return@KeyboardActions
                        onSearch(cityName,navController)
                        cityName = ""
                        keyboardController?.hide()
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,

                        ),
                    shape = RoundedCornerShape(15.dp),


                    )
            }
        }
    }
}

fun onSearch(cityName: String, navController: NavController) {
    navController.navigate(WeatherScreens.MainScreen.name+"/$cityName") {
        popUpTo(WeatherScreens.MainScreen.name)
    }
    Log.d("", cityName)
}