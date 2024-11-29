package com.example.mvvmbrushup.ui.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.data.model.ExchangeDataItem
import com.example.mvvmbrushup.ui.search.components.SearchBar
import com.example.mvvmbrushup.ui.search.components.SearchListItem

@Composable
fun SearchScreen(
    navController: NavController,
    coinId: String?,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchData = viewModel.state.collectAsState()

    Log.d("get coin id", coinId.toString())

    when (searchData) {
        is Resource.Loading<*> -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success<*> -> {
            val searchList = (searchData as Resource.Success<List<ExchangeDataItem>>).data
            searchList?.let { getSearchList(it) }

        }

        is Resource.Error<*> -> {
            val message = (searchData as Resource.Error<List<ExchangeDataItem>>).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message ?: "An error occurred", color = MaterialTheme.colorScheme.error)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        SearchBar(
            onSearchTextChanged = { newText ->
                Log.e("get text", newText)

            }
        )
    }
}

@Composable
fun getSearchList(list: List<ExchangeDataItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(list) { item ->
            SearchListItem(
                item
            ) {

            }
        }
    }
}