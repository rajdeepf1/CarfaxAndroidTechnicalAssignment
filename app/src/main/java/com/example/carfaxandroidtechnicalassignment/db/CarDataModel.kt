package com.example.carfaxandroidtechnicalassignment.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarDataModel(
    @PrimaryKey(autoGenerate = false)
    var id : String = "",
    var carImage: String = "",
    var year: Int = 0,
    var make: String = "",
    var model: String = "",
    var trim: String = "",
    var currentPrice: Double=0.0,
    var mileage: Int=0,
    var dealerAddress: String="",
    var phone: String="",
    var exteriorColor: String="",
    var interiorColor: String="",
    var drivetype: String="",
    var transmission: String="",
    var bodytype: String="",
    var engine: String="",
    var displacement: String="",
    var fuel: String="",
)