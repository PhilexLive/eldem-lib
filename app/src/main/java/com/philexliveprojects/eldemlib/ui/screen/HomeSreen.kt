package com.philexliveprojects.eldemlib.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.ui.AppViewModelProvider
import com.philexliveprojects.eldemlib.ui.GLOBAL
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.theme.EldemLibTheme
import com.philexliveprojects.eldemlib.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onSearchClicked: (String) -> Unit = {},
    onRecentClicked: (Long) -> Unit = {},
    onCategoryClicked: (String) -> Unit = {},
    onBottomSheetExpand: () -> Unit = {},
    editMode: Boolean = false
) {
    val recentArticles by viewModel.recentArticles.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val tempCategories by viewModel.tempCategories.collectAsState()

    var createCategory by remember { mutableStateOf(false) }
    var createCategoryError by remember { mutableStateOf(false) }
    var creatingCategory by remember { mutableStateOf("") }
    CategoryCreationDialog(
        value = creatingCategory,
        onDialogTextInput = {
            createCategoryError = false
            if (it.length < 20) creatingCategory = it
        },
        onDismissRequest = {
            creatingCategory = ""
            createCategory = false
        },
        onAcceptRequest = {
            if (creatingCategory.isNotEmpty()) {
                viewModel.addTempCategory(creatingCategory)
                creatingCategory = ""
                createCategory = false
            } else {
                createCategoryError = true
            }

        },
        isError = createCategoryError,
        show = createCategory
    )

    var deletingCategory by remember { mutableStateOf("") }
    var deleteCategory by remember { mutableStateOf(false) }
    DeletionDialog(
        text = stringResource(R.string.delete_category, deletingCategory),
        deleting = deletingCategory,
        onDismissRequest = {
            deletingCategory = ""
            deleteCategory = false
        },
        onAcceptRequest = {
            viewModel.deleteCategory(deletingCategory)
            viewModel.deleteTempCategory(deletingCategory)
            deletingCategory = ""
            deleteCategory = false
        },
        show = deleteCategory
    )

    Scaffold(
        topBar = {
            TopAppBar {
                IconButton(onClick = onBottomSheetExpand) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(R.string.preferences)
                    )
                }
            }
        },
        floatingActionButton = {
            if (editMode) {
                FloatingActionButton(onClick = { createCategory = true }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_new_category)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = modifier.fillMaxSize()
    ) { contentPadding ->
        HomeList(
            recentArticles = recentArticles,
            categories = (categories + tempCategories).sorted(),
            modifier = Modifier.padding(contentPadding),
            onSearchClick = onSearchClicked,
            onRecentClick = onRecentClicked,
            onCategoryClick = onCategoryClicked,
            onCategoryLongClick = {
                if (editMode) {
                    deletingCategory = it
                    deleteCategory = true
                }
            }
        )
    }
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
fun DeletionDialog(
    text: String,
    deleting: Any,
    onDismissRequest: () -> Unit,
    onAcceptRequest: (Any) -> Unit,
    modifier: Modifier = Modifier,
    show: Boolean = false
) {
    Box(modifier.fillMaxSize()) {
        if (show) {
            Dialog(onDismissRequest = onDismissRequest) {
                Column {
                    Text(text)
                    Row {
                        Button(
                            onClick = onDismissRequest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.cancel))
                        }
                        Button(
                            onClick = { onAcceptRequest(deleting) },
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

@Preview
@Composable
fun GroupCardPreview() {
    EldemLibTheme {
        CategoryCard(title = "Group", imgUrl = "")
    }
}