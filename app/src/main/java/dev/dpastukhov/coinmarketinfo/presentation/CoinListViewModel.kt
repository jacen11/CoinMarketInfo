package dev.dpastukhov.coinmarketinfo.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val interactor: CoinInteractor,
    private val repository: CoinRepository,
    private val serviceApi: CoinMarketServiceApi
) : ViewModel() {

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.call()
        Log.e("CoinListViewModelError", throwable.message.orEmpty())
    }
    private val defaultScope = viewModelScope + defaultExceptionHandler

    lateinit var listCoin: Flow<PagingData<Coin>>
    val isLoading = MutableLiveData<Boolean>()
    val error: SingleLiveEvent<Unit> = SingleLiveEvent()

    val listData: Flow<PagingData<Coin>> = Pager(PagingConfig(pageSize = 15)) { CoinListPagingSource(serviceApi) }.flow.cachedIn(viewModelScope)

    fun setCoinList() {
        defaultScope.launch {
            isLoading.value = true
            listCoin = repository.getCoinList()
            isLoading.value = false
        }
    }
}
