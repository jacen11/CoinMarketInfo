package dev.dpastukhov.coinmarketinfo.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dpastukhov.coinmarketinfo.SingleLiveEvent
import dev.dpastukhov.coinmarketinfo.data.CoinDto
import dev.dpastukhov.coinmarketinfo.domain.CoinInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

class CoinListViewModel @Inject  constructor(private val interactor: CoinInteractor) : ViewModel() {

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.call()
        Log.e("CoinListViewModelError", throwable.message.orEmpty())
    }
    private val defaultScope = viewModelScope + defaultExceptionHandler

    val listCoin = MutableLiveData<List<CoinDto>>()
    val error: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun setCoinList() {
        defaultScope.launch {
            listCoin.value = interactor.getCoinList()
        }
    }
}
