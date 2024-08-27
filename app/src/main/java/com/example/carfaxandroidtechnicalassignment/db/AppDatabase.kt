package com.example.carfaxandroidtechnicalassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDAO() : AppDao
}