package dev.dpastukhov.coinmarketinfo.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoinDto(
    val id: Int? = null,
    val name: String = "",
    val symbol: String? = null,
    val slug: String? = null,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Int? = null,
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("max_supply")
    val maxSupply: Any? = null,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double? = null,
    @SerializedName("total_supply")
    val totalSupply: Double? = null,
    val platform: Any? = null,
    @SerializedName("cmc_rank")
    val cmcRank: Int? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    val quote: Quote? = null,
)