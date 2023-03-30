package com.philexliveprojects.eldemlib.ui.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.compose.home.CategoryCard
import com.philexliveprojects.eldemlib.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    goToCategory: (Long) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                SearchBar(
                    placeholderText = stringResource(R.string.search),
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.clickable { },
                    enabled = false
                )
            }
        }
        items(uiState.categories, key = { it.categoryId }) { category ->
            CategoryCard(
                title = category.categoryName,
                onClick = { goToCategory(category.categoryId) },
                onLongClick = { TODO("Implement long click for category card.") }
            )
        }
    }
}
