package com.example.mvvmbrushup

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmbrushup.ui.Screen
import com.example.mvvmbrushup.ui.coin_detail.CoinDetailScreen
import com.example.mvvmbrushup.ui.coin_list.components.CoinListScreen
import com.example.mvvmbrushup.ui.search.SearchScreen
import com.example.mvvmbrushup.ui.theme.MVVMBrushupTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        var coinId = ""
        enableEdgeToEdge()
        setContent {
            MVVMBrushupTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route,
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen(onSearchClick = {
                                coinId = it
                                navController.navigate(Screen.SearchScreen.route + "/$coinId")
                            }
                            )
                        }
                        composable(
                            route = Screen.SearchScreen.route + "/{coinId}"
                        ) {
                            SearchScreen(navController,coinId)
                        }
                    }

                }

            }
        }
    }
}