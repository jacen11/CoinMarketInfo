package dev.dpastukhov.coinmarketinfo.domain

import dev.dpastukhov.coinmarketinfo.data.CoinRepository
import javax.inject.Inject


class CoinInteractor @Inject constructor(private val repository: CoinRepository) {
    suspend fun getCoinList(start: Int = 1, limit: Int = 10, convert: String = "BTC") =
        repository.getCoinList(start, limit, convert)
}