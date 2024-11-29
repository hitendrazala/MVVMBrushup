package com.example.mvvmbrushup.ui.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbrushup.common.Constant
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.repository.CoinDetailRepository
import com.example.mvvmbrushup.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SearchDetailState())

    val state: StateFlow<SearchDetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let { coinId ->
            Log.e("viewmodel",coinId)
            getSearchDetail(coinId)
        }
    }

    private fun getSearchDetail(coinId: String) {
        viewModelScope.launch {
            repository.getSearchList(coinId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = SearchDetailState(true)
                    }
                    is Resource.Success -> {
                        _state.value = SearchDetailState(exchanges = result.data.orEmpty())
                    }
                    is Resource.Error -> {
                        _state.value = SearchDetailState(error = result.message ?: "An unexpected error occurred")

                    }
                }

            }
        }

    }
}