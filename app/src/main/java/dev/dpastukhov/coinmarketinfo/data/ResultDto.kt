package dev.dpastukhov.coinmarketinfo.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResultDto(
    @SerializedName("status")
    val status: Status? = null,
    @SerializedName("data")
    val data: List<CoinDto>
)

class Status(
    @SerializedName("timestamp")
    val timestamp: String? = null,
    @SerializedName("error_code")
    val errorCode: Int? = null,
    @SerializedName("error_message")
    val errorMessage: Any? = null,
    @SerializedName("elapsed")
    val elapsed: Int? = null,
    @SerializedName("credit_count")
    val creditCount: Int? = null,
    @SerializedName("notice")
    val notice: Any? = null,
    @SerializedName("total_count")
    val totalCount: Int? = null,
)