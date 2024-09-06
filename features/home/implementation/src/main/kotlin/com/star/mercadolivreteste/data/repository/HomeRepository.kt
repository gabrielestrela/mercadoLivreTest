package com.star.mercadolivreteste.data.repository

import com.star.mercadolivreteste.data.datasource.HomeDataSource
import com.star.mercadolivreteste.domain.mapper.HomeDataToDomainMapper

class HomeRepository(
    private val dataSource: HomeDataSource,
    private val mapper: HomeDataToDomainMapper
) {
    suspend fun search(query: String) =
        mapper.map(dataSource.searchProduct(query))

    suspend fun details(id: String) =
        mapper.map(dataSource.productDetails(id))
}
