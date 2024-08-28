package com.example.carfaxandroidtechnicalassignment.api

import com.example.carfaxandroidtechnicalassignment.models.DataSetModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface Api {

    @GET("/assignment.json")
    fun getData(): Observable<DataSetModel>
}