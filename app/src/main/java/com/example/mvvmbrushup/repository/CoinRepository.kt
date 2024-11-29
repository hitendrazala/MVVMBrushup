package com.example.mvvmbrushup.repository

import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.network.CoinApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinApiInterface
) {
    fun getCoins(): Flow<Resource<List<CoinData>>> = flow {
        try {
            emit(Resource.Loading<List<CoinData>>())
            val coins = api.getCoins()
            emit(Resource.Success<List<CoinData>>(coins))

        }catch (e : HttpException){
            emit(Resource.Error<List<CoinData>>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e : IOException){
            emit(Resource.Error<List<CoinData>>("Couldn't reach server. Check your internet connection"))
        }catch (e : Exception){
            emit(Resource.Error<List<CoinData>>("Something went wrong"))
        }
    }
}