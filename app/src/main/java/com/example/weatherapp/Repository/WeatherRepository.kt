package com.example.weatherapp.Repository

import com.example.weatherapp.Data.DataOrException
import com.example.weatherapp.Models.Weather
import com.example.weatherapp.Models.WeatherObject
import com.example.weatherapp.Network.WeatherAPI
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val api: WeatherAPI
) {
    suspend fun getWeather(cityQuery: String)
            : DataOrException<Weather, Boolean, Exception> {
        // 1. API call successful or exception
        val response = try {
            api.getWeather(query = cityQuery)
        }catch (e:Exception){
            return DataOrException(e = e) // returning an object of DataOrException
        }
        return DataOrException(data = response)
    }
}