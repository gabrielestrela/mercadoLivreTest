package com.star.mercadolivreteste.data.datasource

import com.star.mercadolivreteste.data.model.response.HomeItemDetailResponse
import com.star.mercadolivreteste.data.model.response.HomeSearchResponse

interface HomeDataSource {

    suspend fun searchProduct(query: String): HomeSearchResponse

    suspend fun productDetails(id: String) : HomeItemDetailResponse
}
