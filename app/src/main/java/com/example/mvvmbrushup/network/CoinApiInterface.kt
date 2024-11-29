package com.example.mvvmbrushup.network

import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.data.model.CoinDetailsDto
import com.example.mvvmbrushup.data.model.ExchangeData
import com.example.mvvmbrushup.data.model.ExchangeDataItem
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

interface CoinApiInterface {

    @Throws(IOException::class)
    @GET("v1/coins")
    suspend fun getCoins() : List<CoinData>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String) : CoinDetailsDto

    @GET("v1/coins/{coinId}/exchanges")
    suspend fun getAllExchanges(@Path("coinId") coinId: String) : List<ExchangeDataItem>
}