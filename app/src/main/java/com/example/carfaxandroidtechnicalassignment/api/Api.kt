package com.example.carfaxandroidtechnicalassignment.api

import com.example.carfaxandroidtechnicalassignment.models.DataSetModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {

    @GET("/assignment.json")
    suspend fun getData(): Response<DataSetModel>
}