package dev.dpastukhov.coinmarketinfo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.dpastukhov.coinmarketinfo.domain.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepository @Inject constructor(private val serviceApi: CoinMarketServiceApi) {
    suspend fun getCoinList(start: Int = 1, limit: Int = 15, convert: String = "USD"): Flow<PagingData<Coin>> =
        Pager(PagingConfig(pageSize = 15)) { CoinListPagingSource(serviceApi) }.flow
}