package com.example.weatherapp.Widgets

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.ui.theme.fontFamily2

@Composable
fun TopSearchBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = title,
                fontFamily = fontFamily2,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600
            )
        },
        actions = {

        },
        navigationIcon = {

        },
        elevation = elevation

    )
}