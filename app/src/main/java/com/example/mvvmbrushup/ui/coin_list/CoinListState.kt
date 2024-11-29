package com.example.mvvmbrushup.ui.coin_list

import com.example.mvvmbrushup.data.model.CoinData

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinData> = emptyList(),
    val error: String = ""
)
