package com.example.carfaxandroidtechnicalassignment.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carfaxandroidtechnicalassignment.models.Listings
import com.example.carfaxandroidtechnicalassignment.viewmodels.MainViewModel

@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel = hiltViewModel()
    val categories: State<List<Listings>> = mainViewModel.carList.collectAsState()

    Log.d("Raj--->", "CategoryScreen: ${categories.value.toString()}")

//    if (categories.value.isEmpty()) {
//        Box(
//            modifier = Modifier.fillMaxSize(1f),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium)
//        }
//    } else {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            contentPadding = PaddingValues(8.dp),
//            verticalArrangement = Arrangement.SpaceAround,
//        ) {
//            items(categories.value.distinct()) {
//                CategoryItem(category = it, onClick)
//            }
//        }
//    }


}