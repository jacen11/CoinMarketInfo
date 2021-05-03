package dev.dpastukhov.coinmarketinfo.domain

import dev.dpastukhov.coinmarketinfo.data.CoinDto

data class Coin(
    val name: String? = null,
    val price: Double? = null,
    val percentChange1h: Double? = null,
)

fun CoinDto.toModel() = Coin(name = name, price = quote?.usd?.price, percentChange1h = quote?.usd?.percentChange1h)