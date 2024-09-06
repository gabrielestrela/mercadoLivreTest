package com.star.mercadolivreteste.domain.mapper

import com.star.corekotlin.mapper.Mapper
import com.star.corekotlin.primitives.doubles.negativeOne
import com.star.corekotlin.primitives.longint.negativeOne
import com.star.corekotlin.primitives.string.empty
import com.star.mercadolivreteste.data.model.response.HomeItemDetailResponse
import com.star.mercadolivreteste.data.model.response.HomeItemResponse
import com.star.mercadolivreteste.data.model.response.HomeSearchResponse
import com.star.mercadolivreteste.domain.model.HomeItem
import com.star.mercadolivreteste.domain.model.HomeItemDetailResult
import com.star.mercadolivreteste.domain.model.HomeSearchResult

class HomeDataToDomainMapper {
    fun map(from: HomeSearchResponse): HomeSearchResult =
        HomeSearchResult(
            results = from.results?.map() ?: listOf()
        )

    fun map(from: HomeItemDetailResponse): HomeItemDetailResult =
        HomeItemDetailResult(
            title = from.title.orEmpty(),
            price = from.price ?: Double.negativeOne
        )

    private fun List<HomeItemResponse>.map(): List<HomeItem> =
        this.map { HomeItem(
            id = it.id ?: String.empty,
            title = it.title ?: String.empty,
            price = it.price ?: Double.negativeOne
        ) }.toSet().toList()
}
