package com.example.carfaxandroidtechnicalassignment.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.carfaxandroidtechnicalassignment.R
import com.example.carfaxandroidtechnicalassignment.models.Listings
import com.example.carfaxandroidtechnicalassignment.viewmodels.DetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@Composable
fun DetailScreen(navController: NavController,detailViewModel: DetailViewModel) {

    val data = detailViewModel.dataFlow.collectAsState().value
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data!!.images.firstPhoto.large).crossfade(true).build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(220.dp),
            error = painterResource(id = R.drawable.error_image_loading),
        )
        Text(text = "Detail")
    }

}
