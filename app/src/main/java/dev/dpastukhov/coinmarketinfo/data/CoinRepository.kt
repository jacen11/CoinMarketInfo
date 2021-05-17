package dev.dpastukhov.coinmarketinfo.data

import javax.inject.Inject

class CoinRepository @Inject constructor(private val serviceApi: CoinMarketServiceApi) {
    suspend fun getCoinList(start: Int = 1, limit: Int = 15, convert: String = "USD") = serviceApi.getCoinList(start, limit, convert)
}