package dev.dpastukhov.coinmarketinfo.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMarketServiceApi {

    @GET("cryptocurrency/listings/latest/")
    suspend fun getCoinList(
        @Query("start") start: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("convert") convert: String = "BTC"
    ): List<CoinDto>

}