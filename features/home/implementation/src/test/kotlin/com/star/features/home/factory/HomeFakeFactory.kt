package com.star.features.home.factory

import com.star.corekotlin.primitives.doubles.negativeOne
import com.star.corekotlin.primitives.string.empty
import com.star.mercadolivreteste.data.model.response.HomeItemDetailResponse
import com.star.mercadolivreteste.data.model.response.HomeItemResponse
import com.star.mercadolivreteste.data.model.response.HomeSearchResponse
import com.star.mercadolivreteste.domain.model.HomeItem
import com.star.mercadolivreteste.domain.model.HomeItemDetailResult
import com.star.mercadolivreteste.domain.model.HomeSearchResult

fun makeHomeSearchResultFake(
    results: List<HomeItem> = listOf(makeHomeItem())
) = HomeSearchResult(
    results = results
)

fun makeHomeItem(
    id: String = String.empty,
    title: String = String.empty,
    price: Double = Double.negativeOne
) = HomeItem(
    id = id,
    title = title,
    price = price
)

fun makeHomeItemDetailResultFake(
    title: String = String.empty,
    price: Double = Double.negativeOne
) = HomeItemDetailResult(
    title = title,
    price = price,
)

fun makeHomeSearchResponseFake(
    results: List<HomeItemResponse> = listOf(makeHomeItemResponseFake())
) = HomeSearchResponse(
    results = results
)
fun makeHomeItemResponseFake(
    id: String = String.empty,
    title: String = String.empty,
    price: Double = Double.negativeOne
) = HomeItemResponse(
    id = id,
    title = title,
    price = price
)

fun makeHomeItemDetailResponseFake(
    title: String = String.empty,
    price: Double = Double.negativeOne
) = HomeItemDetailResponse(
    title = title,
    price = price
)