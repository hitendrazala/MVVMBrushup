package com.example.mvvmbrushup.ui.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.Mockito.`when`
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _coins = MutableStateFlow<Resource<List<CoinData>>>(Resource.Loading())

    val coinsData: StateFlow<Resource<List<CoinData>>> = _coins

    init {
        fetchCoinData()
    }
    fun fetchCoinData() {
        viewModelScope.launch {
            repository.getCoins().collect { result ->
                _coins.value = result
            }
        }
    }
}