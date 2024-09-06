package com.star.mercadolivreteste.presentation.procedure

sealed interface HomeProcedure {
    data class Search(val query: String) : HomeProcedure
    data class UpdateInputText(val text: String) : HomeProcedure
    data object ResetInput : HomeProcedure
    data object ClearInput : HomeProcedure
    data object ActivateInput: HomeProcedure
}
