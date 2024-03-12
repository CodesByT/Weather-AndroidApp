package com.example.weatherapp.Widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.ui.theme.fontFamily2
import com.example.weatherapp.ui.theme.fontFamily4

@OptIn(ExperimentalMaterial3Api::class)
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
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(top = 15.dp,bottom = 15.dp,start = 10.dp,end = 10.dp).shadow(
            elevation = elevation,
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xC4FFFFFF)
        ),
        title = {
            Text(
                text = "⛅ WeatherScope ⛅",
                fontFamily = fontFamily4,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.W600
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Icon")
                }
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Back screen",
                    tint = Color.Black,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                     }
                )
            }else{
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Invisible icon",
                    tint = Color.Transparent,
                )
            }
        },

    )
}