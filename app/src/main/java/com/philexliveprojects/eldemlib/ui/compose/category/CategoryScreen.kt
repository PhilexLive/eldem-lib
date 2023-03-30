package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
    onArticleClick: (Long) -> Unit = {},
    onSearchClicked: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                SearchBar(
                    placeholderText = stringResource(R.string.search_in, uiState.category),
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.clickable { TODO("Implement onClick in searchBar") },
                    enabled = false
                )
            }
        }
        items(uiState.articles, key = { it.articleId }) { article ->
            Article(
                title = article.title,
                description = article.description,
                onClick = { onArticleClick(article.articleId) },
                onLongClick = { TODO("Implement long click for article") }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Article(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Surface(modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .combinedClickable(onClick = onClick, onLongClick = onLongClick)
        ) {
            Text(text = title)
            Text(description)
            Divider()
        }
    }
}