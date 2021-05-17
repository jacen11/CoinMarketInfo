package dev.dpastukhov.coinmarketinfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dev.dpastukhov.coinmarketinfo.SingleLiveEvent
import dev.dpastukhov.coinmarketinfo.data.CoinListPagingSource
import dev.dpastukhov.coinmarketinfo.domain.CoinInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val interactor: CoinInteractor,
) : ViewModel() {

    val error: SingleLiveEvent<Unit> = SingleLiveEvent()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val listData = try {
        Pager(PagingConfig(pageSize = 15)) { CoinListPagingSource(interactor) }.flow.cachedIn(viewModelScope)
    } catch (ex: Exception) {
        error.call()
        null
    }

}
