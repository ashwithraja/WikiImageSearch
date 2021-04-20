package com.mahadream.wikiimagesearch.data.common


import com.google.gson.annotations.SerializedName

open class BaseApiResponseModel<T> {
    @SerializedName("data")
    val `data`: T? = null

    @SerializedName("status_code")
    val statusCode: String? = null

    @SerializedName("message")
    val message: String? = null
}