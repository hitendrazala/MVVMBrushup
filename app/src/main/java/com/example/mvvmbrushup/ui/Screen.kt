package com.example.mvvmbrushup.ui

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin_list_screen")
    object CoinDetailScreen : Screen("coin_detail_screen")
    object SearchScreen : Screen("search_screen")
    object ContactsDirectoryScreen : Screen("contacts_directory_screen")
    object SplashScreen : Screen("splash_screen")
}