package com.star.features.home.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.star.features.home.factory.makeHomeItemDetailResponseFake
import com.star.features.home.factory.makeHomeSearchResponseFake
import com.star.mercadolivreteste.data.datasource.HomeDataSource
import com.star.mercadolivreteste.data.model.response.HomeItemDetailResponse
import com.star.mercadolivreteste.data.model.response.HomeSearchResponse
import com.star.mercadolivreteste.data.repository.HomeRepository
import com.star.mercadolivreteste.domain.mapper.HomeDataToDomainMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.rules.TestRule
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeRepositoryTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dataSource: HomeDataSource by lazy { mockk() }
    private val mapper = HomeDataToDomainMapper()

    private val repository: HomeRepository by lazy {
        HomeRepository(dataSource = dataSource, mapper = mapper)
    }

    @Test
    fun onSearch_returnMapperData() = runTest {
        val expected = mapper.map(makeHomeSearchResponseFake())
        prepareScenario()

        val actual = repository.search("query")

        assertEquals(expected, actual)
    }

    @Test
    fun onDetails_returnMapperData() = runTest {
        val expected = mapper.map(makeHomeItemDetailResponseFake())
        prepareScenario()

        val actual = repository.details("someId")

        assertEquals(expected, actual)
    }

    private fun prepareScenario(
        dataSourceSearchResult: HomeSearchResponse = makeHomeSearchResponseFake(),
        dataSourceDetailsResult: HomeItemDetailResponse = makeHomeItemDetailResponseFake()
    ) {
        coEvery { dataSource.searchProduct(any()) } returns dataSourceSearchResult
        coEvery { dataSource.productDetails(any()) } returns dataSourceDetailsResult
    }
}