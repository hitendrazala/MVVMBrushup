package com.example.mvvmbrushup.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDetailsDto(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "is_active")
    val isActive: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "tags")
    val tags: List<Tag>,
    @Json(name = "team")
    val team: List<Team>,
)