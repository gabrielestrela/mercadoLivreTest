package com.star.mercadolivreteste.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.mercadolivreteste.presentation.procedure.HomeProcedure
import com.star.mercadolivreteste.presentation.ui.searchinput.SearchInput
import com.star.mercadolivreteste.presentation.ui.searchitem.SearchItem
import com.star.mercadolivreteste.presentation.viewmodel.HomeViewModel
import com.star.mercadolivreteste.presentation.viewstate.HomeViewState
import com.star.mercadolivreteste.presentation.viewstate.SearchInputState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    toDetailsScreen: (title: String, price: String) -> Unit,
    modifier: Modifier
) {
    val viewState = viewModel.state.collectAsState().value

    HomeScreenLayout(
        toDetailsScreen = toDetailsScreen,
        procedure = viewModel::dispatchProcedure,
        viewState = viewState,
        modifier = modifier
    )
}

@Composable
fun HomeScreenLayout(
    toDetailsScreen: (title: String, price: String) -> Unit,
    procedure: (HomeProcedure) -> Unit,
    viewState: HomeViewState,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchInput(
            state = viewState.inputState,
            inputText = viewState.inputText,
            onInputChange = { text: String ->
                procedure(HomeProcedure.UpdateInputText(text))
            },
            onClear = { procedure(HomeProcedure.ClearInput) },
            onClick = { procedure(HomeProcedure.ActivateInput) },
            onChevronClick = { if (viewState.inputText.isNotBlank()) procedure(HomeProcedure.Search(viewState.inputText)) }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
        when {
            viewState.loading -> Text(text = "Loading", modifier.fillMaxSize())
            viewState.searchResult.isNotEmpty() -> {
                LazyColumn(
                    modifier = modifier.fillMaxSize().padding(horizontal = 4.dp),
                ) {
                    items(
                        count = viewState.searchResult.size,
                        key = { idx -> viewState.searchResult[idx].id }
                    ) { idx ->
                        SearchItem(
                            itemName = viewState.searchResult[idx].title,
                            price = viewState.searchResult[idx].price,
                            id = viewState.searchResult[idx].id,
                            onItemClick = {
                                toDetailsScreen(
                                    viewState.searchResult[idx].title,
                                    viewState.searchResult[idx].price
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Default",
    showBackground = true,
)
@Composable
fun HomeScreenPreview() {
    HomeScreenLayout(
        toDetailsScreen = { _, _ -> },
        procedure = {},
        viewState = HomeViewState(),
        modifier = Modifier
    )
}