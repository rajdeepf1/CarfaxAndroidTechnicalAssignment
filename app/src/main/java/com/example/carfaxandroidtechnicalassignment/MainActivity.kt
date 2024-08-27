package com.example.carfaxandroidtechnicalassignment

import android.os.Bundle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carfaxandroidtechnicalassignment.screens.MainScreen
import com.example.carfaxandroidtechnicalassignment.ui.theme.CarfaxAndroidTechnicalAssignmentTheme
import com.example.carfaxandroidtechnicalassignment.ui.theme.toolBarColor
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CarfaxAndroidTechnicalAssignmentTheme {
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
                        App(

                        )
                    }
                }
            }
        }
    }


}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main-screen") {
        composable(route = "main-screen") {
            MainScreen()
        }

//        composable(route = "detail/{}", arguments = listOf(
//            navArgument("") {
//                type = NavType.StringType
//            }
//        ))
//        {
//            DetailScreen()
//        }
    }
}


