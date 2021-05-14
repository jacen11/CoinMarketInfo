package dev.dpastukhov.coinmarketinfo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.dpastukhov.coinmarketinfo.domain.Coin
import dev.dpastukhov.coinmarketinfo.domain.toModel
import javax.inject.Inject

class CoinListPagingSource @Inject constructor(
    private val service: CoinMarketServiceApi,
) : PagingSource<Int, Coin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val nextPage = params.key ?: 1

            val data = service.getCoinList(
                start = params.key ?: 1,
                limit = params.loadSize
            ).data

            LoadResult.Page(
                data = data.map { it.toModel() },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
