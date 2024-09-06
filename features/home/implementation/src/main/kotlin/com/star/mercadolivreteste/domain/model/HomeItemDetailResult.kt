package com.star.mercadolivreteste.domain.model

import com.star.corekotlin.primitives.doubles.negativeOne
import com.star.corekotlin.primitives.string.empty

data class HomeItemDetailResult(
    val title: String = String.empty,
    val price: Double = Double.negativeOne
)
