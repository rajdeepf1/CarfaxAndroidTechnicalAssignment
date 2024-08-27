package com.example.carfaxandroidtechnicalassignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.carfaxandroidtechnicalassignment.screens.DetailScreen
import com.example.carfaxandroidtechnicalassignment.screens.MainScreen
import com.example.carfaxandroidtechnicalassignment.ui.theme.CarfaxAndroidTechnicalAssignmentTheme
import com.example.carfaxandroidtechnicalassignment.ui.theme.toolBarColor
import com.example.carfaxandroidtechnicalassignment.utils.Utils
import com.example.carfaxandroidtechnicalassignment.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        if (!Utils.checkForInternet(applicationContext)){
            Toast.makeText(applicationContext, "No Internet Connection!", Toast.LENGTH_SHORT).show()
        }

        setContent {
            CarfaxAndroidTechnicalAssignmentTheme {
                App()
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){

    val navController = rememberNavController()
    val detailViewModel = remember {
        DetailViewModel()
    }

    // Observe the current back stack entry
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = toolBarColor,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "CARFAX",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(50.dp, 0.dp)
                    )
                },
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = "main-screen") {
                composable(route = "main-screen") {
                    MainScreen(navController,detailViewModel)
                }
                composable(route = "detail-screen")
                {
                    DetailScreen(navController,detailViewModel)
                }
            }
        }
    }
}



