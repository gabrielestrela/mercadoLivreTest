package com.star.mercadolivreteste.domain.model

import com.star.corekotlin.primitives.doubles.negativeOne
import com.star.corekotlin.primitives.longint.negativeOne
import com.star.corekotlin.primitives.string.empty

data class HomeSearchResult(
    val results: List<HomeItem> = listOf()
)

data class HomeItem(
    val id: String = String.empty,
    val title: String = String.empty,
    val price: Double = Double.negativeOne
)
