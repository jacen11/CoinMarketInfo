package dev.dpastukhov.coinmarketinfo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class CoinListPagingSource @Inject constructor (
    private val service: CoinMarketServiceApi,
) : PagingSource<Int, CoinDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinDto> {
        return try {
            val nextPage = params.key ?: 1

            val data = service.getCoinList(
                start = params.key ?: 1,
                limit = params.loadSize
            ).data

            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override  fun getRefreshKey(state: PagingState<Int, CoinDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
