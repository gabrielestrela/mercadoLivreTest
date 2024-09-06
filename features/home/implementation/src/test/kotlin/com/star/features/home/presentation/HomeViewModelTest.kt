package com.star.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.star.corekotlin.coroutine.DispatcherProvider
import com.star.corekotlin.primitives.string.empty
import com.star.corekotlin.test.CoroutineTestRule
import com.star.features.home.factory.makeHomeSearchResultFake
import com.star.mercadolivreteste.data.repository.HomeRepository
import com.star.mercadolivreteste.domain.model.HomeSearchResult
import com.star.mercadolivreteste.presentation.procedure.HomeProcedure
import com.star.mercadolivreteste.presentation.viewmodel.HomeViewModel
import com.star.mercadolivreteste.presentation.viewstate.HomeUiItem
import com.star.mercadolivreteste.presentation.viewstate.SearchInputState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.rules.TestRule
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val dispatcher: DispatcherProvider = coroutineTestRule.testDispatcherProvider
    private val repository: HomeRepository by lazy { mockk() }

    private val viewModel: HomeViewModel by lazy {
        HomeViewModel(
            dispatcher = dispatcher,
            repository = repository
        )
    }

    @Test
    fun onUpdateInputText_updateTextValueOnState() {
        prepareScenario()

        val expected = "update"
        viewModel.dispatchProcedure(HomeProcedure.UpdateInputText(expected))

        val actual = viewModel.state.value.inputText

        assertEquals(expected, actual)
    }

    @Test
    fun onResetInput_setTextValueAsEmpty() {
        prepareScenario()

        val expected = String.empty
        viewModel.dispatchProcedure(HomeProcedure.ResetInput)

        val actual = viewModel.state.value.inputText

        assertEquals(expected, actual)
    }

    @Test
    fun onResetInput_setInputStateAsIdle() {
        prepareScenario()

        val expected = SearchInputState.IDLE
        viewModel.dispatchProcedure(HomeProcedure.ResetInput)

        val actual = viewModel.state.value.inputState

        assertEquals(expected, actual)
    }

    @Test
    fun onClearInput_setTextValueAsEmpty() {
        prepareScenario()

        val expected = String.empty
        viewModel.dispatchProcedure(HomeProcedure.ClearInput)

        val actual = viewModel.state.value.inputText

        assertEquals(expected, actual)
    }

    @Test
    fun onActivateInput_setInputStateAsEmptyActive() {
        prepareScenario()

        val expected = SearchInputState.EMPTY_ACTIVE
        viewModel.dispatchProcedure(HomeProcedure.ActivateInput)

        val actual = viewModel.state.value.inputState

        assertEquals(expected, actual)
    }

    @Test
    fun onActivateInput_withInputWithText_setInputStateAsActive() {
        prepareScenario()

        val expected = SearchInputState.ACTIVE
        viewModel.dispatchProcedure(HomeProcedure.UpdateInputText("update"))
        viewModel.dispatchProcedure(HomeProcedure.ActivateInput)

        val actual = viewModel.state.value.inputState

        assertEquals(expected, actual)
    }

    @Test
    fun onSearch_updateStateWithResponseList() = runTest {
        val expectedResult = makeHomeSearchResultFake()
        prepareScenario(repositoryResult = expectedResult)

        viewModel.dispatchProcedure(HomeProcedure.Search("anything"))

        val expected = listOf(
            HomeUiItem(
                title = expectedResult.results.first().title,
                price = expectedResult.results.first().price.toString(),
                id = expectedResult.results.first().id
            )
        )

        val actual = viewModel.state.value.searchResult

        assertEquals(expected, actual)
    }

    private fun prepareScenario(repositoryResult: HomeSearchResult = makeHomeSearchResultFake()) {
        coEvery { repository.search(any()) } returns repositoryResult
    }
}