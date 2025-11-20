package com.ekky.smarthome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ekky.smarthome.ui.home.HomeScreen
import com.ekky.smarthome.ui.home.HomeViewModel
import com.ekky.smarthome.ui.roomdetail.RoomDetailScreen
import com.ekky.smarthome.ui.theme.SmartHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SmartHomeTheme {   // <<< wrap all UI here

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        val viewModel: HomeViewModel = viewModel()
                        HomeScreen(
                            viewModel = viewModel,
                            onRoomClick = { id ->
                                navController.navigate("roomDetail/$id")
                            }
                        )
                    }

                    composable(
                        route = "roomDetail/{roomId}",
                        arguments = listOf(navArgument("roomId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        RoomDetailScreen(
                            roomId = backStackEntry.arguments?.getString("roomId") ?: ""
                        )
                    }
                }
            }
        }
    }
}