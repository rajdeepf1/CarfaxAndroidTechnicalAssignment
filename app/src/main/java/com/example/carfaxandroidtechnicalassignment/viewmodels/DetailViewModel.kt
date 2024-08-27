package com.example.carfaxandroidtechnicalassignment.viewmodels

import androidx.lifecycle.ViewModel
import com.example.carfaxandroidtechnicalassignment.models.Listings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailViewModel : ViewModel(){

    private val _dataFlow = MutableStateFlow<Listings?>(null)
    val dataFlow: StateFlow<Listings?> = _dataFlow

    // Function to set data in the Flow
    fun setData(newData: Listings) {
        _dataFlow.value = newData // Update the flow with new data
    }

}