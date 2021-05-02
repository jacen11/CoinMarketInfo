package dev.dpastukhov.coinmarketinfo.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResultDto(
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("data")
    var data: List<CoinDto>? = null
)

class Status(
    @SerializedName("timestamp")
    var timestamp: String? = null,
    @SerializedName("error_code")
    var errorCode: Int? = null,
    @SerializedName("error_message")
    var errorMessage: Any? = null,
    @SerializedName("elapsed")
    var elapsed: Int? = null,
    @SerializedName("credit_count")
    var creditCount: Int? = null,
    @SerializedName("notice")
    var notice: Any? = null,
    @SerializedName("total_count")
    var totalCount: Int? = null,
)