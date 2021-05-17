package dev.dpastukhov.coinmarketinfo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.dpastukhov.coinmarketinfo.domain.Coin
import dev.dpastukhov.coinmarketinfo.domain.CoinInteractor

class CoinListPagingSource constructor(
    private val interactor: CoinInteractor,
) : PagingSource<Int, Coin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val nextPage = params.key ?: 1

            val data: List<Coin> = interactor.getCoinList(
                start = params.key ?: 1,
                limit = params.loadSize
            )

            LoadResult.Page(
                data = data,
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
