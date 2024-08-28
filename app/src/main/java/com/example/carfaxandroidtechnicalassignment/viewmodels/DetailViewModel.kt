package com.example.carfaxandroidtechnicalassignment.viewmodels

import androidx.lifecycle.ViewModel
import com.example.carfaxandroidtechnicalassignment.db.CarDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {

    private val _dataFlow = MutableStateFlow<CarDataModel?>(null)
    val dataFlow: StateFlow<CarDataModel?> = _dataFlow

    fun setData(newData: CarDataModel) {
        _dataFlow.value = newData
    }

}