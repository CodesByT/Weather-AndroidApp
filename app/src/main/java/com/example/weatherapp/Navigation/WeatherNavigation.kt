package com.example.weatherapp.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.Screens.AboutScreen.AboutScreen
import com.example.weatherapp.Screens.FavouriteScreen.FavouritesScreen
import com.example.weatherapp.Screens.SearchScreen.SearchScreen
import com.example.weatherapp.Screens.SettingsScreen.SettingsScreen
import com.example.weatherapp.Screens.main.MainViewModel
import com.example.weatherapp.Screens.main.WeatherMainScreen
import com.example.weatherapp.Screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable(
            "$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                }
            )
        ){navBack->
            navBack.arguments?.getString("city").let { city->
                val mainViewModel = hiltViewModel<MainViewModel>()
                WeatherMainScreen(navController = navController, mainViewModel,city = city)
            }
        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }

        composable(WeatherScreens.FavouriteScreen.name){
            FavouritesScreen(navController = navController)
        }

        composable(WeatherScreens.SettingScreen.name){
            SettingsScreen(navController = navController)
        }


    }
}

