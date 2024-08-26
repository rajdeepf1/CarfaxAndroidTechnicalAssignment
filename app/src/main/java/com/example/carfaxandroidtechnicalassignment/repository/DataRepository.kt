package com.example.carfaxandroidtechnicalassignment.repository

import com.example.carfaxandroidtechnicalassignment.api.Api
import com.example.carfaxandroidtechnicalassignment.models.Listings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataRepository @Inject constructor(private  val api: Api) {

    private val _carList = MutableStateFlow<List<Listings>>(emptyList())
    val carList: StateFlow<List<Listings>>
        get() = _carList


    suspend fun getList(){
        val response = api.getData()
        if (response.isSuccessful && response.body() != null){
            _carList.emit(response.body()!!.listings)
        }
    }

}