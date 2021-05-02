package dev.dpastukhov.coinmarketinfo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListPagingSource @Inject constructor (
    val backend: CoinMarketServiceApi,
    // val query: String
) : PagingSource<Int, CoinDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinDto> {
        return try {
            val nextPage = params.key ?: 1
            val response = backend.getCoinList(limit = params.loadSize)

            val data = backend.getCoinList(
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
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
