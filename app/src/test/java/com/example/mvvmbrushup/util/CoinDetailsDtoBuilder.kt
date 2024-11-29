package com.example.mvvmbrushup.util

import com.example.mvvmbrushup.data.model.CoinDetailsDto
import com.example.mvvmbrushup.data.model.Tag
import com.example.mvvmbrushup.data.model.Team

class CoinDetailsDtoBuilder {
    var description: String = "Bitcoin is a decentralized digital currency, without a central bank or single administrator."
    var id: String = "bitcoin"
    var isActive: Boolean = true
    var name: String = "Bitcoin"
    var rank: Int = 1
    var symbol: String = "BTC"
    var tags: List<Tag> = listOf(
        Tag(
            coinCounter = 10000,
            icoCounter = null,
            id = "cryptocurrency",
            name = "Cryptocurrency"
        ),
        Tag(
            coinCounter = 5000,
            icoCounter = 50,
            id = "payment",
            name = "Payment"
        )
    )
    var team: List<Team> = listOf(
        Team(
            id = "satoshi",
            name = "Satoshi Nakamoto",
            position = "Founder"
        ),
        Team(
            id = "developer1",
            name = "Developer One",
            position = "Core Developer"
        )
    )

    fun build(): CoinDetailsDto {
        return CoinDetailsDto(
            description = description,
            id = id,
            isActive = isActive,
            name = name,
            rank = rank,
            symbol = symbol,
            tags = tags,
            team = team
        )
    }
}