package com.example.carfaxandroidtechnicalassignment.viewmodels

import androidx.lifecycle.ViewModel
import com.example.carfaxandroidtechnicalassignment.db.CarDataModel
import com.example.carfaxandroidtechnicalassignment.models.Listings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailViewModel : ViewModel(){

    private val _dataFlow = MutableStateFlow<CarDataModel?>(null)
    val dataFlow: StateFlow<CarDataModel?> = _dataFlow

    // Function to set data in the Flow
    fun setData(newData: CarDataModel) {
        _dataFlow.value = newData // Update the flow with new data
    }

}