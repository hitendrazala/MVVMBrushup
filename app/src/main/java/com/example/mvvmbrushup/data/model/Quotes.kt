package com.example.mvvmbrushup.data.model


import com.squareup.moshi.Json

data class Quotes(
    @Json(name = "USD")
    val uSD: USD
)