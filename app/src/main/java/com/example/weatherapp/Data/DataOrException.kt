package com.example.weatherapp.Data

//Generic Class
class DataOrException <T, Boolean, E: Exception>(
    // on every api call
    // 1. API call successful or exception
    // then loading will be true
    // 2. We got the data or JSON is empty
    var data : T? = null,
    var loading:Boolean? = null,
    var e: E? = null
)