package com.example.weatherapp.Screens.FavouriteScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.Models.Favourite
import com.example.weatherapp.Navigation.WeatherScreens
import com.example.weatherapp.Widgets.TopSearchBar
import com.example.weatherapp.ui.theme.fontFamily2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navController: NavController) {
    val favouriteScreenViewModel: FavouriteScreenViewModel = hiltViewModel()
    val list = favouriteScreenViewModel.favlist.collectAsState().value
    Scaffold(
        topBar = {
            TopSearchBar(
                navController = navController,
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                title = "           Favourites cities ❤️"
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyColumn() {
                    items(items = list) {
                        CityRow(it, navController = navController, favouriteScreenViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favourite: Favourite,
    navController: NavController,
    favouriteScreenViewModel: FavouriteScreenViewModel
) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favourite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xB5000000)
    ) {
        Row(
            modifier = Modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(favourite.city+", ${favourite.countryCode}", color = Color.White, fontFamily = fontFamily2, fontSize = 22.sp)
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(24.dp)
                    .clickable {
                        favouriteScreenViewModel.deleteFavourite(favourite)
                    },
                imageVector = Icons.Filled.Delete,
                contentDescription = "Invisible icon",
                tint = Color.Red,
            )
        }
    }
}
