package com.example.carfaxandroidtechnicalassignment.repository

import com.example.carfaxandroidtechnicalassignment.api.Api
import com.example.carfaxandroidtechnicalassignment.db.AppDatabase
import com.example.carfaxandroidtechnicalassignment.db.CarDataModel
import com.example.carfaxandroidtechnicalassignment.models.DataSetModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val api: Api,
    private val appDatabase: AppDatabase
) {

    fun getData(): Observable<DataSetModel> {
        return api.getData()
    }

    fun getDataFromRoomDB(): Observable<List<CarDataModel>> {
        return appDatabase.getDAO().getListings()
    }

    fun setDataToRoomDB(carDataModel: CarDataModel) {
        return appDatabase.getDAO().addListings(carDataModel)
    }

}

