package com.star.mercadolivreteste.presentation.ui

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.mercadolivreteste.presentation.procedure.DetailsProcedure
import com.star.mercadolivreteste.presentation.viewmodel.DetailsViewModel
import com.star.mercadolivreteste.presentation.viewstate.DetailsViewState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    title: String,
    price: String,
    modifier: Modifier
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = title+price) {
        viewModel.dispatchProcedure(DetailsProcedure.SetDetails(title, price))
    }

    DetailsScreenLayout(
        modifier = modifier,
        viewState = viewState
    )
}

@Composable
fun DetailsScreenLayout(
    modifier: Modifier,
    viewState: DetailsViewState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = modifier.wrapContentSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(10.dp)
            ) {
                Text(text = viewState.title,)
                Spacer(modifier.height(8.dp))
                Text(text = viewState.price)
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenLayoutPreview() {
    DetailsScreenLayout(
        modifier = Modifier,
        viewState = DetailsViewState(
            title = "title",
            price = "2000,00"
        )
    )
}