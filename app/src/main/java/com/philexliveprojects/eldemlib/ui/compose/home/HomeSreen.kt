package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.ui.GLOBAL
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.utilities.ContentType
import com.philexliveprojects.eldemlib.ui.viewmodels.EldemLibUiState


const val HOME_ROUTE = "home"

@Composable
fun HomeScreen(
    contentType: ContentType,
    uiState: EldemLibUiState,
    modifier: Modifier = Modifier,
) {
    if (contentType == ContentType.MultipleListsAndDetail) {

    } else if (contentType == ContentType.ListAndDetail) {

    } else {

    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
}

@Composable
fun CategoryCreationDialog(
    value: String,
    onDialogTextInput: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onAcceptRequest: () -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    show: Boolean = false
) {
    Box(modifier.fillMaxSize()) {
        if (show) {
            Dialog(onDismissRequest) {
                Column {
                    TextField(
                        value = value,
                        onValueChange = { onDialogTextInput(it) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.category_name)
                            )
                        },
                        isError = isError,
                        maxLines = 1
                    )
                    Row {
                        Button(
                            onClick = onDismissRequest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.cancel))
                        }
                        Button(
                            onClick = onAcceptRequest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.add))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeList(
    recentArticles: List<ArticleListItem>,
    categories: List<String>,
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {},
    onRecentClick: (Long) -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onCategoryLongClick: ((String) -> Unit) = {}
) {
    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                SearchBar(
                    placeholderText = stringResource(R.string.search),
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.clickable { onSearchClick(GLOBAL) },
                    enabled = false
                )
            }
        }
        item {
            for (recent in recentArticles) {
                Article(
                    title = recent.title,
                    description = recent.description,
                    onClick = { onRecentClick(recent.articleId) }
                )
            }
        }
        items(categories, key = { it }) { category ->
            CategoryCard(
                title = category,
                imgUrl = category,
                onClick = { onCategoryClick(category) },
                onLongClick = { onCategoryLongClick(category) }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCard(
    title: String,
    imgUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onLongClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            )

    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = null
        )

        Text(
            text = title,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            style = MaterialTheme.typography.h4
        )
    }
}