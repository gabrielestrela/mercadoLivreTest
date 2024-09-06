package com.star.mercadolivreteste.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeSearchResponse(
    @SerialName("results")
    val results: List<HomeItemResponse>? = null
)

@Serializable
data class HomeItemResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("price")
    val price: Double? = null,
)
