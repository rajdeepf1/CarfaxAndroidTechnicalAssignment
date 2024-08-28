package com.example.carfaxandroidtechnicalassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListings(data: CarDataModel)

    @Query("SELECT * FROM CarDataModel")
    fun getListings(): Observable<List<CarDataModel>>
}