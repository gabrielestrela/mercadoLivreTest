package com.star.mercadolivreteste.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeItemDetailResponse(
    @SerialName("title")
    val title: String? = null,
    @SerialName("price")
    val price: Double? = null,
)
