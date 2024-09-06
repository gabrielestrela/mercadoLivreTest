package com.star.mercadolivreteste.data.datasource

import com.star.mercadolivreteste.data.model.response.HomeItemDetailResponse
import com.star.mercadolivreteste.data.model.response.HomeSearchResponse
import com.star.network.client.MercadoLivreClient
import com.star.network.dsl.request

class HomeRemoteDataSource(
    private val client: MercadoLivreClient
) : HomeDataSource {

    override suspend fun searchProduct(query: String) =
        client.httpClient.request<Any, HomeSearchResponse> {
            url = "${client.baseUrl}$SITE_ID/search"
            parameters = listOf(QUERY to query)
        }

    override suspend fun productDetails(id: String): HomeItemDetailResponse =
        client.httpClient.request<Any, HomeItemDetailResponse> {
            url = "${client.baseUrl}$SITE_ID/items"
            parameters = listOf(IDS to id)
        }

    companion object {
        private const val SITE_ID = "MLB"
        private const val QUERY = "q"
        private const val IDS = "ids"
    }
}
