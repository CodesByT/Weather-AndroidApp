package com.example.weatherapp.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.Models.Favourite

// Database Structure is created
// weatherDAO and Favourite table is entered

// Since we arre using dependency injection, so we will
// create the database in the App Module
@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDAO():WeatherDAO

}