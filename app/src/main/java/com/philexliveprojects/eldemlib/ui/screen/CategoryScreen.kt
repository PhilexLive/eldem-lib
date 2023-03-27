package com.philexliveprojects.eldemlib.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.ui.AppViewModelProvider
import com.philexliveprojects.eldemlib.ui.GLOBAL
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    category: String,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onArticleClick: (Long) -> Unit = {},
    onSearchClicked: (String) -> Unit = {},
    editMode: Boolean = false
) {

    val articles by viewModel.articles.collectAsState()
//    var deletingArticle by remember { mutableStateOf(Long) }
//    var deleteArticle by remember { mutableStateOf(false) }
//    DeletionDialog(
//        text = stringResource(id = R.string.delete_article, ),
//        deleting = ,
//        onDismissRequest = { /*TODO*/ },
//        onAcceptRequest =
//    )

    CategoryList(
        category = category,
        articles = articles,
        modifier = modifier,
        onArticleClick = onArticleClick,
        onSearchClick = { onSearchClicked(category) },
        onArticleLongClick = {},
        editMode = editMode
    )
}

@Composable
fun CategoryList(
    category: String,
    articles: List<ArticleListItem>,
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {},
    onArticleClick: (Long) -> Unit = {},
    onArticleLongClick: ((Long) -> Unit) = {},
    editMode: Boolean = false
) {
    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                SearchBar(
                    placeholderText = stringResource(R.string.search_in, category),
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.clickable { onSearchClick(GLOBAL) },
                    enabled = false
                )
            }
        }
        items(articles, key = { it }) { article ->
            Article(
                title = article.title,
                description = article.description,
                onClick = { onArticleClick(article.articleId) },
                onLongClick = { if (editMode) onArticleLongClick(article.articleId) }
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