package com.star.mercadolivreteste.presentation.ui.searchinput

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.mercadolivreteste.homeImpl.R
import com.star.mercadolivreteste.presentation.viewstate.SearchInputState

@Composable
fun SearchInput(
    state: SearchInputState,
    inputText: String,
    onInputChange: (String) -> Unit,
    onClear: () -> Unit,
    onClick: () -> Unit,
    onChevronClick: () -> Unit
) {
    TextField(
        value = inputText,
        onValueChange = { newValue: String -> onInputChange(newValue) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        leadingIcon = { ClearIcon(onClear = onClear)},
        trailingIcon = { TrailingIcon(state = state, action = onChevronClick) },
        colors = when (state) {
            SearchInputState.IDLE -> idleColors()
            else -> activeColors()
        },
        placeholder = { Text(text = stringResource(R.string.search_input_placeholder)) },
        interactionSource = remember { MutableInteractionSource() }.also { source ->
            LaunchedEffect(key1 = source) {
                source.interactions.collect { interaction ->
                    if (interaction is PressInteraction.Release) onClick.invoke()
                }
            }
        }
    )
}

@Composable
fun ClearIcon(
    onClear: () -> Unit,
) {
    IconButton(onClick = onClear) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = stringResource(id = R.string.accessibility_clear)
        )
    }
}

@Composable
fun TrailingIcon(state: SearchInputState, action: () -> Unit) {
    when (state) {
        SearchInputState.EMPTY_ACTIVE,
        SearchInputState.IDLE -> SearchIcon()
        SearchInputState.ACTIVE -> ChevronIcon(onChevronClick = action)
    }
}

@Composable
fun SearchIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(id = R.string.accessibility_search)
    )
}

@Composable
fun ChevronIcon(onChevronClick: () -> Unit) {
    IconButton(onClick = onChevronClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.accessibility_clear)
        )
    }
}


@Composable
private fun idleColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedTextColor = Color.Black,
    disabledTextColor = Color.Black.copy(alpha = 0.6f),
    cursorColor = Color.Transparent,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
)

@Composable
private fun activeColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Yellow.copy(alpha = 0.2f),
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedTextColor = Color.Black,
    disabledTextColor = Color.Yellow.copy(alpha = 0.8f),
    cursorColor = Color.Yellow,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
)

@Preview
@Composable
fun SearchInputPreview() {
    SearchInput(
        state = SearchInputState.ACTIVE,
        inputText = "Preview",
        onInputChange = {},
        onClear = {},
        onClick = {},
        onChevronClick = {}
    )
}