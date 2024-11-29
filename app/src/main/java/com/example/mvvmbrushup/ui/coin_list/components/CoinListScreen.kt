package com.example.mvvmbrushup.ui.coin_list.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.ui.Screen
import com.example.mvvmbrushup.ui.coin_list.CoinListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {

    val coinData by viewModel.coinsData.collectAsState()

    when (coinData) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val coin = (coinData as Resource.Success<List<CoinData>>).data

            coin?.let { getList(it, navController) }
        }

        is Resource.Error -> {
            val message = (coinData as Resource.Error<CoinData>).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message ?: "An error occurred", color = MaterialTheme.colorScheme.error)
            }
        }
    }


}

@Composable
fun getList(coinData: List<CoinData>, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(coinData) { item ->
                CoinListItem(
                    item,
                    onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${item.id}")

                    }
                )
            }
        }

    }
}

