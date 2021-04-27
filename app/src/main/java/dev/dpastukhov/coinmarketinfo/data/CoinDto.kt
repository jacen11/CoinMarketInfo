package dev.dpastukhov.coinmarketinfo.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class CoinDto(
    @SerializedName("id")
    private var id: Int? = null,

    @SerializedName("name")
    private var name: String? = null,

    @SerializedName("symbol")
    private var symbol: String? = null,

    @SerializedName("slug")
    private var slug: String? = null,

    @SerializedName("num_market_pairs")
    private var numMarketPairs: Int? = null,

    @SerializedName("date_added")
    private var dateAdded: String? = null,

    @SerializedName("max_supply")
    private var maxSupply: Any? = null,

    @SerializedName("circulating_supply")
    private var circulatingSupply: Double? = null,

    @SerializedName("total_supply")
    private var totalSupply: Double? = null,

    @SerializedName("platform")
    private var platform: Any? = null,

    @SerializedName("cmc_rank")
    private var cmcRank: Int? = null,

    @SerializedName("last_updated")
    private var lastUpdated: String? = null,

    @SerializedName("quote")
    private var quote: Quote? = null,

    )