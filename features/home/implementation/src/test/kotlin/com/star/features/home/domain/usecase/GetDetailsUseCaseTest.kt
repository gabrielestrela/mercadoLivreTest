package com.star.features.home.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.star.features.home.factory.makeHomeItemDetailResultFake
import com.star.mercadolivreteste.data.repository.HomeRepository
import com.star.mercadolivreteste.domain.model.HomeItemDetailResult
import com.star.mercadolivreteste.domain.usecase.GetDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertEquals

class GetDetailsUseCaseTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val repository: HomeRepository by lazy { mockk() }
    private val useCase: GetDetailsUseCase by lazy {
        GetDetailsUseCase(repository = repository)
    }

    @Test
    fun onGetDetails_returnDataWithSuccess() = runTest {
        val expected = makeHomeItemDetailResultFake()
        prepareScenario(useCaseResult = expected)

        val actual = useCase("id")

        assertEquals(expected, actual)
    }

    private fun prepareScenario(
        useCaseResult: HomeItemDetailResult = makeHomeItemDetailResultFake()
    ) {
        coEvery { useCase.invoke(any()) } returns useCaseResult
    }
}