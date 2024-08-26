package com.example.carfaxandroidtechnicalassignment.models

data class DataSetModel(
    val backfillCount: Int,
    val dealerNewCount: Int,
    val dealerUsedCount: Int,
    val enhancedCount: Int,
    val listings: List<Listings>,
    val page: Int,
    val pageSize: Int,
    val searchArea: SearchArea,
    val totalListingCount: Int,
    val totalPageCount: Int
)