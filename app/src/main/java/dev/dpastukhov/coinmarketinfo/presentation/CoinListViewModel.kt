package dev.dpastukhov.coinmarketinfo.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.dpastukhov.coinmarketinfo.SingleLiveEvent
import dev.dpastukhov.coinmarketinfo.data.CoinListPagingSource
import dev.dpastukhov.coinmarketinfo.data.CoinMarketServiceApi
import dev.dpastukhov.coinmarketinfo.data.CoinRepository
import dev.dpastukhov.coinmarketinfo.domain.Coin
import dev.dpastukhov.coinmarketinfo.domain.CoinInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val interactor: CoinInteractor,
    private val repository: CoinRepository,
    private val serviceApi: CoinMarketServiceApi
) : ViewModel() {

    val error: SingleLiveEvent<Unit> = SingleLiveEvent()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val listData = Pager(PagingConfig(pageSize = 15)) { CoinListPagingSource(serviceApi) }.flow.cachedIn(viewModelScope)


}
