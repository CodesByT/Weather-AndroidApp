package com.example.weatherapp.Repository

import com.example.weatherapp.Data.WeatherDAO
import com.example.weatherapp.Models.Favourite
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
class WeatherDBRepository @Inject constructor(
    private val weatherDAO: WeatherDAO
){
    fun getFavourites(): Flow<List<Favourite>> = weatherDAO.getFavourite()
    suspend fun insertFavourite(favourite: Favourite) = weatherDAO.insertFavourite(favourite)
    suspend fun updateFavourite(favourite: Favourite) = weatherDAO.updateFavourite(favourite)

    suspend fun deleteFavourite(favourite: Favourite) = weatherDAO.deleteFavourite(favourite)
    suspend fun getFavById(city: String) = weatherDAO.getFavById(city)

}