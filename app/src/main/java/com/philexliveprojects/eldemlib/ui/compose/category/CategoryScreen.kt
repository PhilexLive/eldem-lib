package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.common.rememberSearchState
import com.philexliveprojects.eldemlib.ui.compose.category.ArticlePreview
import com.philexliveprojects.eldemlib.ui.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
    onArticleClick: (Long) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchState = rememberSearchState("")

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                value = searchState.value,
                onValueChange = { if (it.length < 120) searchState.onSearch(it) },
                placeholder = {
                    Text(
                        buildAnnotatedString {
                            append(stringResource(R.string.search_in))
                            withStyle(style = SpanStyle(Color.Green)) {
                                append(uiState.category.categoryName)
                            }
                        }
                    )
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            items(uiState.articles, key = { it.articleId }) { article ->
                ArticlePreview(
                    title = article.title,
                    description = article.description,
                    onClick = { onArticleClick(article.articleId) },
                )
            }
        }
    }
}
