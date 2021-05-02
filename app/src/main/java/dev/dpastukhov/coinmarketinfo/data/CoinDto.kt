package dev.dpastukhov.coinmarketinfo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Keep
class CoinDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("slug")
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
    @SerializedName("platform")
    val platform: Any? = null,
    @SerializedName("cmc_rank")
    val cmcRank: Int? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("quote")
    val quote: Quote? = null,
)