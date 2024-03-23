package com.example.weatherapp.Screens.FavouriteScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Models.Favourite
import com.example.weatherapp.Repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._dagger_hilt_android_internal_lifecycle_HiltWrapper_HiltViewModelFactory_ViewModelModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val weatherDBRepository: WeatherDBRepository
) : ViewModel() {

    private val _favlist = MutableStateFlow<List<Favourite>>(emptyList())
    val favlist = _favlist.asStateFlow()

    init {
        // We will get all the favourites from the list immediately after creating FavouriteViewModel
        viewModelScope.launch (Dispatchers.IO){
            weatherDBRepository.getFavourites().distinctUntilChanged().collect { listofFavs ->
                if (listofFavs.isNullOrEmpty()) {
                    Log.d("", "")
                } else {
                    _favlist.value = listofFavs
                }
            }
        }
    }

    fun insertFavourite(favourite: Favourite) = viewModelScope.launch {
        weatherDBRepository.insertFavourite(favourite)
    }
    fun updateFavourite(favourite: Favourite) = viewModelScope.launch {
        weatherDBRepository.updateFavourite(favourite)
    }
    fun deleteFavourite(favourite: Favourite) = viewModelScope.launch {
        weatherDBRepository.deleteFavourite(favourite)
    }
    fun getFavouriteByCityName(city: String) = viewModelScope.launch {
        weatherDBRepository.getFavById(city)
    }


}