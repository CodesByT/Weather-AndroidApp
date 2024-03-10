package com.example.weatherapp.DependencyInjection

import com.example.weatherapp.Network.WeatherAPI
import com.example.weatherapp.Utilitites.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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


}