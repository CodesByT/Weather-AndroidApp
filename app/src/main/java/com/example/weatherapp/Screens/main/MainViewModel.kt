package com.example.weatherapp.Screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Data.DataOrException
import com.example.weatherapp.Models.Weather
import com.example.weatherapp.Repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }
//    val data: MutableState<DataOrException<Weather, Boolean, Exception>> =
//        mutableStateOf(DataOrException(null, true, Exception("")))
//
//    init {
//        loadWeather()
//    }
//
//    private fun loadWeather() {
//        getWeather("Seattle")
//    }
//
//    private fun getWeather(city: String) {
//        viewModelScope.launch {
//            if (city.isEmpty()) return@launch // empty return from coroutine scope
//            data.value.loading = true // means data is loading
//            data.value = repository.getWeather(city) // get the data
//            if (data.value.data.toString().isNotEmpty()) {
//                data.value.loading = false
//            }
//        }
//        Log.d("GET", "getWeather: ${data.value.data.toString()}")
//    }
}