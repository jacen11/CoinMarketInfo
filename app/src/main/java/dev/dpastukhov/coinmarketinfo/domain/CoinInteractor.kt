package dev.dpastukhov.coinmarketinfo.domain

import androidx.paging.map
import dev.dpastukhov.coinmarketinfo.data.CoinRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CoinInteractor @Inject constructor(private val repository: CoinRepository) {
    suspend fun getCoinList(start: Int = 1, limit: Int = 15, convert: String = "USD") =
        repository.getCoinList(start, limit, convert).map { pagingData -> pagingData.map { coinDto -> coinDto.toModel() } }
}