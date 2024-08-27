package com.example.carfaxandroidtechnicalassignment.repository

import android.content.Context
import android.util.Log
import com.example.carfaxandroidtechnicalassignment.api.Api
import com.example.carfaxandroidtechnicalassignment.db.AppDatabase
import com.example.carfaxandroidtechnicalassignment.db.CarDataModel
import com.example.carfaxandroidtechnicalassignment.models.Listings
import com.example.carfaxandroidtechnicalassignment.utils.Utils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class DataRepository @Inject constructor(private  val api: Api,private val appDatabase: AppDatabase,@ApplicationContext val context: Context) {

    private val _carList = MutableStateFlow<List<CarDataModel>>(emptyList())
    val carList: StateFlow<List<CarDataModel>>
        get() = _carList


    suspend fun getList(){

        if (Utils.checkForInternet(context)){
            val response = api.getData()
            if (response.isSuccessful && response.body() != null){
                var mutableList = mutableListOf<CarDataModel>()
                val apiData = response.body()!!.listings

                for (i in apiData){
                    var  carDataModel = CarDataModel()
                    carDataModel.id = i.id
                    carDataModel.model = i.model
                    carDataModel.carImage = i.images.firstPhoto.large
                    carDataModel.make = i.make
                    carDataModel.trim = i.trim
                    carDataModel.bodytype = i.bodytype
                    carDataModel.currentPrice = i.currentPrice
                    carDataModel.dealerAddress = i.dealer.address
                    carDataModel.displacement = i.displacement
                    carDataModel.drivetype = i.drivetype
                    carDataModel.engine = i.engine
                    carDataModel.exteriorColor = i.exteriorColor
                    carDataModel.interiorColor = i.interiorColor
                    carDataModel.fuel = i.fuel
                    carDataModel.mileage = i.mileage
                    carDataModel.transmission = i.transmission
                    carDataModel.year = i.year
                    carDataModel.phone = i.dealer.phone

                    mutableList.add(carDataModel)
                }

                _carList.emit(mutableList)


                CoroutineScope(Dispatchers.IO).launch {
                    for (i in mutableList){
                        appDatabase.getDAO().addListings(i)
                    }
                }


            }else {
                val data = appDatabase.getDAO().getListings()
                _carList.emit(data)
            }
        }else{
            //No Internet Connection
            val data = appDatabase.getDAO().getListings()
            _carList.emit(data)
        }


    }

}