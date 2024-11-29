package com.example.mvvmbrushup.repository

import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinDetailsDto
import com.example.mvvmbrushup.network.CoinApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CoinDetailRepository @Inject constructor(
    private val api: CoinApiInterface
) {
    fun getCoinDetail(coinId: String): Flow<Resource<CoinDetailsDto>> = flow {
        try {
            emit(Resource.Loading<CoinDetailsDto>())
            val coinDetail = api.getCoinDetails(coinId)
            emit(Resource.Success<CoinDetailsDto>(coinDetail))
        } catch (e: Exception) {
            emit(
                Resource.Error<CoinDetailsDto>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetailsDto>("Couldn't reach server. Check your internet connection."))
        }

    }
}