package com.example.weatherapp.DependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.Data.WeatherDAO
import com.example.weatherapp.Data.WeatherDatabase
import com.example.weatherapp.Network.WeatherAPI
import com.example.weatherapp.Utilitites.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // class for all the modules to create things like
    // databases, dependency injections, repositories
    // to instantiate or make providers for them

    @Provides
    @Singleton
    fun provideOpenWeatherAPI(): WeatherAPI {
        // Retrofit converts all the JSON that we are getting from the API, into kotlin objects
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDAO(weatherDatabase: WeatherDatabase):WeatherDAO {
        return  weatherDatabase.weatherDAO()
    }

    // Creating Weather Database, using dependency injection
    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "Weather_Database")
            .fallbackToDestructiveMigration()
            .build()
    }


}