package dev.dpastukhov.coinmarketinfo.domain

import dev.dpastukhov.coinmarketinfo.data.CoinDto
import java.math.BigDecimal
import java.math.RoundingMode

data class Coin(
    val name: String,
    val price: BigDecimal,
    val percentChange1h: BigDecimal,
)

fun CoinDto.toModel() = Coin(
    name = name,
    price = BigDecimal.valueOf(quote?.usd?.price ?: 0.0).setScale(2, RoundingMode.HALF_UP),
    percentChange1h = BigDecimal.valueOf(quote?.usd?.percentChange1h ?: 0.0).setScale(2, RoundingMode.HALF_UP)
)