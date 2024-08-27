package com.example.carfaxandroidtechnicalassignment.screens

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.carfaxandroidtechnicalassignment.R
import com.example.carfaxandroidtechnicalassignment.ui.theme.buttonTextColor
import com.example.carfaxandroidtechnicalassignment.ui.theme.carNameColor
import com.example.carfaxandroidtechnicalassignment.ui.theme.infoLabel
import com.example.carfaxandroidtechnicalassignment.utils.Utils.startCall
import com.example.carfaxandroidtechnicalassignment.viewmodels.DetailViewModel


@Composable
fun DetailScreen(navController: NavController, detailViewModel: DetailViewModel) {

    val data = detailViewModel.dataFlow.collectAsState().value

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


    Column(
        Modifier
            .background(Color.White)
            .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column(modifier = Modifier.weight(1f,true)) {


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data!!.images.firstPhoto.large).crossfade(true).build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                error = painterResource(id = R.drawable.error_image_loading),
            )
            Text(
                text = "${data.year} ${data.make} ${data.model} ${data.trim}",
                modifier = Modifier.padding(25.dp, 8.dp),
                fontWeight = FontWeight.Bold,
                color = carNameColor,
                fontSize = 18.sp
            )
            Text(
                text = "\$ ${data.currentPrice}  |  ${data.mileage} k mi",
                modifier = Modifier.padding(25.dp, 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .padding(2.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.extraSmall,
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Text(
                    text = "Vehicle Info",
                    modifier = Modifier.padding(20.dp, 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row {
                    Column(modifier = Modifier.padding(20.dp, 8.dp)) {
                        Text(
                            text = "Location",
                            color = infoLabel
                        )
                        Text(
                            text = "Exterior Color",
                            color = infoLabel
                        )
                        Text(
                            text = "Interior Color",
                            color = infoLabel
                        )
                        Text(
                            text = "Drive Type",
                            color = infoLabel
                        )
                        Text(
                            text = "Transmission",
                            color = infoLabel
                        )
                        Text(
                            text = "Body Style",
                            color = infoLabel
                        )
                        Text(
                            text = "Engine",
                            color = infoLabel
                        )
                        Text(
                            text = "Fuel",

                            color = infoLabel
                        )

                    }

                    Column(
                        modifier = Modifier.padding(20.dp, 8.dp)
                    ) {
                        Text(
                            text = data.dealer.address,
                            color = Color.Black,
                        )
                        Text(
                            text = data.exteriorColor,
                            color = Color.Black
                        )
                        Text(
                            text = data.interiorColor,
                            color = Color.Black
                        )
                        Text(
                            text = data.drivetype,
                            color = Color.Black
                        )
                        Text(
                            text = data.transmission,
                            color = Color.Black
                        )
                        Text(
                            text = data.bodytype,
                            color = Color.Black
                        )
                        Text(
                            text = "${data.engine} ${data.displacement}",
                            color = Color.Black
                        )
                        Text(
                            text = data.fuel,
                            color = Color.Black
                        )
                    }
                }



            }



        }
        TextButton(
            onClick = {
                if (hasCallPermission) {
                    // Make a direct call
                    startCall(context, data!!.dealer.phone)
                } else {
                    // Request the permission
                    requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(buttonTextColor),
        ) {
            Text("CALL DEALER", color = Color.White)
        }
    }

}

