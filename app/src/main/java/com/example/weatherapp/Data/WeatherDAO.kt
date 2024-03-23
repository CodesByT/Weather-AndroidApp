package com.example.weatherapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.Models.Favourite
import kotlinx.coroutines.flow.Flow

// DAO stands for Data access Object
@Dao
interface WeatherDAO {
    // All the queries will be entered here
    // All the CRUD

    // To handle the reactivity of our data in favourite List, we will use Flows
    @Query(value = "SELECT * FROM fav_tbl ")
    fun getFavourite(): Flow<List<Favourite>>

    @Query("Select * from fav_tbl where city = :city")
    suspend fun getFavById(city:String):Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)
    @Query("Select * from fav_tbl")
    suspend fun deleteAllFavourite()

    @Delete
    suspend fun deleteAllFavourite(favourite: Favourite)


}