package dev.dpastukhov.coinmarketinfo.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Quote(
    @SerializedName("USD")
    var usd: Usd? = null,
)

@Keep
class Usd(
    @SerializedName("price")
    val price: Double,

    @SerializedName("volume_24h")
    val volume24h: Double? = null,

    @SerializedName("percent_change_1h")
    val percentChange1h: Double? = null,

    @SerializedName("percent_change_24h")
    val percentChange24h: Double? = null,

    @SerializedName("percent_change_7d")
    val percentChange7d: Double? = null,

    @SerializedName("percent_change_30d")
    val percentChange30d: Double? = null,

    @SerializedName("percent_change_60d")
    val percentChange60d: Double? = null,

    @SerializedName("percent_change_90d")
    val percentChange90d: Double? = null,

    @SerializedName("market_cap")
    val marketCap: Double? = null,

    @SerializedName("last_updated")
    val lastUpdated: String? = null,
)
