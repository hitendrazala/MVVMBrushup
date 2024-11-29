package com.example.mvvmbrushup.ui.coin_detail

import com.example.mvvmbrushup.data.model.CoinDetailsDto

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetailsDto? = null,
    val error: String = ""
)
