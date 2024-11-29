package com.example.mvvmbrushup.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String?,
    @Json(name = "position")
    val position: String
)