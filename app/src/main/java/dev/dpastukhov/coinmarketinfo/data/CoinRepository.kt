package dev.dpastukhov.coinmarketinfo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import retrofit2.http.Query
import javax.inject.Inject

class CoinRepository @Inject constructor(private val serviceApi: CoinMarketServiceApi) {
    suspend fun getCoinList(start: Int = 1, limit: Int = 15, convert: String = "USD") =
        Pager(PagingConfig(pageSize = limit)) {
            CoinListPagingSource(serviceApi)
        }.flow
}