package com.star.mercadolivreteste.presentation.ui.searchitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchItem(
    itemName: String,
    price: String,
    id: String,
    onItemClick: (id: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 4.dp)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val paddingModifier = Modifier.padding(4.dp)
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = paddingModifier.clickable { onItemClick(id) }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = itemName)
                Spacer(modifier = Modifier.fillMaxWidth().height(6.dp))
                Text(text = price)
            }
        }
    }
}

@Preview
@Composable
fun SearchItemPreview() {
    SearchItem(itemName = "itemName", price = "price", id = "1", onItemClick = {})
}