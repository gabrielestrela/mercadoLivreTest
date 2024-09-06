package com.star.mercadolivreteste.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.corekotlin.coroutine.DispatcherProvider
import com.star.mercadolivreteste.domain.usecase.GetDetailsUseCase
import com.star.mercadolivreteste.presentation.procedure.DetailsProcedure
import com.star.mercadolivreteste.presentation.procedure.DetailsProcedure.*
import com.star.mercadolivreteste.presentation.viewstate.DetailsViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val dispatcher: DispatcherProvider,
    private val getDetails: GetDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsViewState())
    val state: StateFlow<DetailsViewState> = _state

    fun dispatchProcedure(procedure: DetailsProcedure) {
        when(procedure) {
            is SetDetails -> updateState(procedure.title, procedure.price)
        }
    }

    private fun updateState(title: String, price: String) {
        viewModelScope.launch(dispatcher.io()) {

            _state.update {
                it.copy(
                    title = title,
                    price = price
                )
            }
        }
    }
}