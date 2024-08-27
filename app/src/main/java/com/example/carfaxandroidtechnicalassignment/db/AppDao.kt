package com.example.carfaxandroidtechnicalassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListings(data : CarDataModel)

    @Query("SELECT * FROM CarDataModel")
    suspend fun getListings() : List<CarDataModel>
}