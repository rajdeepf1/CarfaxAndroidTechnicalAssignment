package com.example.carfaxandroidtechnicalassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carfaxandroidtechnicalassignment.db.CarDataModel
import com.example.carfaxandroidtechnicalassignment.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    val carList: StateFlow<List<CarDataModel>>
        get() = repository.carList

    init {
        viewModelScope.launch {
            repository.getList()
        }
    }

}