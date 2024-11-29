package com.example.mvvmbrushup.ui.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbrushup.common.Constant
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.repository.CoinDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())

    val state: StateFlow<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            repository.getCoinDetail(coinId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = CoinDetailState(true)
                    }
                    is Resource.Success -> {
                        _state.value = CoinDetailState(coins = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailState(error = result.message ?: "An unexpected error occurred")

                    }
                }

            }
        }

    }
}