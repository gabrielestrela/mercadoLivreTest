package com.star.mercadolivreteste.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.corekotlin.coroutine.DispatcherProvider
import com.star.corekotlin.primitives.string.empty
import com.star.mercadolivreteste.data.repository.HomeRepository
import com.star.mercadolivreteste.domain.model.HomeItem
import com.star.mercadolivreteste.presentation.procedure.HomeProcedure
import com.star.mercadolivreteste.presentation.procedure.HomeProcedure.*
import com.star.mercadolivreteste.presentation.viewstate.HomeUiItem
import com.star.mercadolivreteste.presentation.viewstate.HomeViewState
import com.star.mercadolivreteste.presentation.viewstate.SearchInputState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dispatcher: DispatcherProvider,
    private val repository: HomeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> = _state

    fun dispatchProcedure(procedure: HomeProcedure) {
        when (procedure) {
            is Search -> doSearch(procedure.query)
            is UpdateInputText -> updateInputText(procedure.text)
            ResetInput -> resetInput()
            ClearInput -> clearInput()
            ActivateInput -> activateInput()
        }
    }

    private fun activateInput() {
        _state.update { it.copy(inputState = inputActiveState()) }
    }

    private fun clearInput() {
        _state.update { it.copy(inputText = String.empty) }
    }

    private fun resetInput() {
        _state.update {
            it.copy(inputText = String.empty, inputState = SearchInputState.IDLE)
        }
    }

    private fun updateInputText(text: String) {
        _state.update {
            it.copy(inputText = text, inputState = inputActiveState())
        }
    }

    private fun inputActiveState(): SearchInputState =
        if (_state.value.inputText.isEmpty()) {
            SearchInputState.EMPTY_ACTIVE
        } else SearchInputState.ACTIVE

    private fun doSearch(query: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
            t.printStackTrace()
        }

        _state.update { it.copy(loading = true) }

        viewModelScope.launch(dispatcher.io() + coroutineExceptionHandler) {
            val result = repository.search(query)

            _state.update {
                it.copy(
                    loading = false,
                    searchResult = result.results.toUi()
                )
            }
        }
    }

    private fun List<HomeItem>.toUi() = this.map {
        HomeUiItem(
            title = it.title,
            price = it.price.toString(),
            id = it.id
        )
    }
}