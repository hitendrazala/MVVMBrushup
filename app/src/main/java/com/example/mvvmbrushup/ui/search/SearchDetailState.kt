package com.example.mvvmbrushup.ui.search

import com.example.mvvmbrushup.data.model.ExchangeDataItem

data class SearchDetailState(
    val isLoading: Boolean = false,
    val exchanges: List<ExchangeDataItem> = emptyList(),
    val error: String = ""
)
