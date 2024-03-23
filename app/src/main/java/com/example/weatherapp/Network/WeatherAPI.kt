package com.example.weatherapp.Network

import com.example.weatherapp.Models.Weather
import com.example.weatherapp.Models.WeatherObject
import com.example.weatherapp.Utilitites.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "data/2.5/forecast?")
    suspend fun getWeather(
        //This is the query from retrofit
        @Query("q") query : String,
        @Query("units") units : String = "metric",
        @Query("appid") appid : String = Constants.API_KEY
    ):Weather // Returning the JSON data into this Kotlin Class
}