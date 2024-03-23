package com.example.weatherapp.Models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl") // Its a ROOM Entity
data class Favourite(

    @PrimaryKey //City will be a primary key
    @ColumnInfo(name="city")
    val city: String,

    @ColumnInfo(name="country")
    val countryCode: String
)
