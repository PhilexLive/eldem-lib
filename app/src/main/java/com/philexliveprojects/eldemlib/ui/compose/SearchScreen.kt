package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.ui.GLOBAL
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.theme.EldemLibTheme
import com.philexliveprojects.eldemlib.ui.viewmodels.SearchViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(),
    searchScope: String = GLOBAL,
    onNavigateBack: () -> Unit = {},
    onUnitPressed: (Int) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    var searchText by viewModel.searchText
    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(
            placeholderText = if (searchScope == GLOBAL) {
                stringResource(R.string.search)
            } else {
                stringResource(R.string.search_in, searchScope)
            },
            value = searchText,
            onValueChange = {
                searchText = it
            },
            focusRequester = focusRequester,
            leadingIcon = {
                IconButton(
                    onClick = { onNavigateBack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(
                        onClick = { viewModel.clearSearchText() }
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        )

        LazyColumn {
            /*TODO SearchScreen: LazyColumn content requred*/
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    EldemLibTheme {
        SearchScreen(searchScope = "Group")
    }
}