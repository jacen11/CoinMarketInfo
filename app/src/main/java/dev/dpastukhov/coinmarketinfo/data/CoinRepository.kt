package dev.dpastukhov.coinmarketinfo.data

import retrofit2.http.Query
import javax.inject.Inject

class CoinRepository @Inject constructor(private val serviceApi: CoinMarketServiceApi) {
    suspend fun getCoinList(start: Int = 1, limit: Int = 10, convert: String = "BTC") =
        serviceApi.getCoinList(start, limit, convert).data
}