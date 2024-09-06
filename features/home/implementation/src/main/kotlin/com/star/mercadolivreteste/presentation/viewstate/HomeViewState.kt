package com.star.mercadolivreteste.presentation.viewstate

import com.star.corekotlin.primitives.longint.negativeOne
import com.star.corekotlin.primitives.string.empty

data class HomeViewState(
    val searchResult: List<HomeUiItem> = listOf(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val inputState: SearchInputState = SearchInputState.IDLE,
    val inputText: String = String.empty
)

data class HomeUiItem(
    val title: String = String.empty,
    val price: String = String.empty,
    val id: String = String.empty
)

enum class SearchInputState {
    IDLE,
    EMPTY_ACTIVE,
    ACTIVE
}
