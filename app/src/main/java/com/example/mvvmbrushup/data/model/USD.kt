package com.example.mvvmbrushup.data.model


import com.squareup.moshi.Json

data class USD(
    @Json(name = "price")
    val price: Double,
    @Json(name = "volume_24h")
    val volume24h: Double
)