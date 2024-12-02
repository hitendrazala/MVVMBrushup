package com.example.mvvmbrushup.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "ContactName") val name: String,
    @Json(name = "Number") val number: String,
    @Json(name = "PreOrder") val preOrder: Int
)