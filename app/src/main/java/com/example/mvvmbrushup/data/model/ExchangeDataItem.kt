package com.example.mvvmbrushup.data.model


import com.squareup.moshi.Json

data class ExchangeDataItem(
    @Json(name = "adjusted_volume_24h_share")
    val adjustedVolume24hShare: Double,
    @Json(name = "base_currency_id")
    val baseCurrencyId: String,
    @Json(name = "base_currency_name")
    val baseCurrencyName: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "exchange_id")
    val exchangeId: String,
    @Json(name = "exchange_name")
    val exchangeName: String,
    @Json(name = "fee_type")
    val feeType: String,
    @Json(name = "last_updated")
    val lastUpdated: String,
    @Json(name = "market_url")
    val marketUrl: String,
    @Json(name = "outlier")
    val outlier: Boolean,
    @Json(name = "pair")
    val pair: String,
    @Json(name = "quote_currency_id")
    val quoteCurrencyId: String,
    @Json(name = "quote_currency_name")
    val quoteCurrencyName: String,
    @Json(name = "quotes")
    val quotes: Quotes,
    @Json(name = "trust_score")
    val trustScore: String
)