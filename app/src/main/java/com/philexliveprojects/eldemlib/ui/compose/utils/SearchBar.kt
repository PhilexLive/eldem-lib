package com.philexliveprojects.eldemlib.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.philexliveprojects.eldemlib.ui.compose.clearFocusOnKeyboardDismiss
import com.philexliveprojects.eldemlib.ui.theme.EldemLibTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .focusRequester(focusRequester)
                .clearFocusOnKeyboardDismiss(),
            enabled = enabled,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            })
        )
    }
}

@Composable
fun rememberSearchState(value: String): SearchState = remember(value) { SearchState(value) }

@Stable
class SearchState(initialValue: String) {
    var value: String = initialValue
        private set

    fun onSearch(value: String) {
        this.value = value
    }

    fun onClearSearch() {
        value = ""
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    EldemLibTheme {
        SearchBar(value = "", onValueChange = {}, trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        })
    }
}

@Preview
@Composable
fun SearchBarPreviewDark() {
    EldemLibTheme(darkTheme = true) {
        SearchBar(value = "", onValueChange = {})
    }
}