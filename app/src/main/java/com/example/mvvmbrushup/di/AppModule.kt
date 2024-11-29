package com.example.mvvmbrushup.di

import com.example.mvvmbrushup.common.Constant
import com.example.mvvmbrushup.network.CoinApiInterface
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideApi() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoinApiInterface {
        return retrofit.create(CoinApiInterface::class.java)
    }
}