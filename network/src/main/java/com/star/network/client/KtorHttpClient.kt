package com.star.network.client

import io.ktor.client.HttpClient

interface KtorHttpClient {
    val baseUrl: String
    val httpClient: HttpClient
}
