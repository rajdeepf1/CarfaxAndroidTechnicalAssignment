package com.example.carfaxandroidtechnicalassignment.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carfaxandroidtechnicalassignment.db.CarDataModel
import com.example.carfaxandroidtechnicalassignment.repository.DataRepository
import com.example.carfaxandroidtechnicalassignment.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _dataList = mutableStateOf<List<CarDataModel>>(emptyList())
    val dataList: State<List<CarDataModel>> = _dataList

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val disposable = CompositeDisposable()

    init {
        if (Utils.checkForInternet(context)) {
            getData()
        } else {
            getDataFromRoomDatabase()
        }

    }

    private fun getData() {
        var mutableList = mutableListOf<CarDataModel>()
        disposable.add(
            repository.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        for (i in response.listings) {
                            val carDataModel = CarDataModel()
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
                        _dataList.value = mutableList
                        _isLoading.value = false
                        for (i in _dataList.value) {
                            repository.setDataToRoomDB(i)
                        }
                    },
                    { error ->
                        Log.d("OnError-->", "getData: ${error.message}")
                        _isLoading.value = false
                    }
                )

        )

    }

    private fun getDataFromRoomDatabase() {
        var mutableList = mutableListOf<CarDataModel>()
        disposable.add(
            repository.getDataFromRoomDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        mutableList.addAll(response)
                        _dataList.value = mutableList
                        _isLoading.value = false
                    },
                    { error ->
                        Log.d("OnError-->", "getDataFromRoomDatabase: ${error.message}")
                        _isLoading.value = false
                    }
                )
        )

    }

}
