package com.example.carfaxandroidtechnicalassignment.models

data class SearchArea(
    val city: String,
    val dynamicRadii: List<Int>,
    val dynamicRadius: Boolean,
    val latitude: Double,
    val longitude: Double,
    val radius: Int,
    val state: String,
    val zip: String
)