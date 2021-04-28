package dev.dpastukhov.coinmarketinfo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Keep
class Quote(
    @SerializedName("USD")
    private var usd: Usd? = null,
)

@Keep
class Usd(
    @SerializedName("price")
    private val price: Double? = null,

    @SerializedName("volume_24h")
    private val volume24h: Double? = null,

    @SerializedName("percent_change_1h")
    private val percentChange1h: Double? = null,

    @SerializedName("percent_change_24h")
    private val percentChange24h: Double? = null,

    @SerializedName("percent_change_7d")
    private val percentChange7d: Double? = null,

    @SerializedName("percent_change_30d")
    private val percentChange30d: Double? = null,

    @SerializedName("percent_change_60d")
    private val percentChange60d: Double? = null,

    @SerializedName("percent_change_90d")
    private val percentChange90d: Double? = null,

    @SerializedName("market_cap")
    private val marketCap: Double? = null,

    @SerializedName("last_updated")
    private val lastUpdated: String? = null,
)
