package com.example.carfaxandroidtechnicalassignment.screens

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.carfaxandroidtechnicalassignment.R
import com.example.carfaxandroidtechnicalassignment.models.Listings
import com.example.carfaxandroidtechnicalassignment.ui.theme.buttonTextColor
import com.example.carfaxandroidtechnicalassignment.utils.Utils.startCall
import com.example.carfaxandroidtechnicalassignment.viewmodels.DetailViewModel
import com.example.carfaxandroidtechnicalassignment.viewmodels.MainViewModel

@Composable
fun MainScreen(navController: NavController,detailViewModel: DetailViewModel) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val data: State<List<Listings>> = mainViewModel.carList.collectAsState()

    if (data.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        LazyColumn(Modifier.fillMaxSize()) {
            items(items = data.value, itemContent = { Item(listings = it, detailViewModel,navController) })
        }
    }


}

@Composable
fun Item(listings: Listings, detailViewModel: DetailViewModel,navController: NavController) {


    val context = LocalContext.current
    var hasCallPermission by remember { mutableStateOf(false) }

    // Register the permissions request
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasCallPermission = isGranted
        if (!isGranted) {
            Toast.makeText(context, "Permission DENIED", Toast.LENGTH_SHORT).show()
        }
    }

    // Check permission state
    LaunchedEffect(key1 = true) {
        hasCallPermission = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
    }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight()
            .clickable {
                detailViewModel.setData(listings)
                       navController.navigate("detail-screen")
                       },
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(4.dp),
    ) {

        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(listings.images.firstPhoto.large).crossfade(true).build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
                error = painterResource(id = R.drawable.error_image_loading),
            )
        }
        Text(
            text = "${listings.year} ${listings.make} ${listings.model} ${listings.trim}",
            modifier = Modifier.padding(12.dp, 4.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "\$ ${listings.currentPrice}  |  ${listings.mileage} k mi",
            modifier = Modifier.padding(12.dp, 4.dp),
        )
        Text(
            text = listings.dealer.address,
            modifier = Modifier.padding(12.dp, 4.dp),

            )

        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(12.dp, 4.dp))

        TextButton(
            onClick = {
                if (hasCallPermission) {
                    // Make a direct call
                    startCall(context, listings.dealer.phone)
                } else {
                    // Request the permission
                    requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "CALL DEALER", color = buttonTextColor, fontWeight = FontWeight.Bold
            )
        }


    }

}





