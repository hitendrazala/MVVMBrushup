package com.example.mvvmbrushup.repository

import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinDetailsDto
import com.example.mvvmbrushup.data.model.ExchangeDataItem
import com.example.mvvmbrushup.network.CoinApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: CoinApiInterface
) {
    fun getSearchList(coinId: String): Flow<Resource<List<ExchangeDataItem>>> = flow {
        try {
            emit(Resource.Loading<List<ExchangeDataItem>>())
            val searchDetail = api.getAllExchanges(coinId)
            emit(Resource.Success<List<ExchangeDataItem>>(searchDetail))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<ExchangeDataItem>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<ExchangeDataItem>>("Couldn't reach server. Check your internet connection."))
        }

    }
}