package com.philexliveprojects.eldemlib.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.data.local.entities.CategoryWithImgUrls
import com.philexliveprojects.eldemlib.ui.AppViewModelProvider
import com.philexliveprojects.eldemlib.ui.GLOBAL
import com.philexliveprojects.eldemlib.ui.common.SearchBar
import com.philexliveprojects.eldemlib.ui.theme.EldemLibTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCategoryClicked: (String) -> Unit = {},
    onSearchClicked: (String) -> Unit = {},
    onBottomSheetExpand: () -> Unit = {},
    editMode: Boolean = false
) {
    val homeDialogUiState by viewModel.homeDialogUiState.collectAsState()

    Box(modifier.fillMaxSize()) {
        if (homeDialogUiState.show) {
            Dialog(onDismissRequest = {
                viewModel.onDialogHide()
            }) {
                Column {
                    TextField(
                        value = homeDialogUiState.input,
                        onValueChange = { viewModel.onDialogTextInput(it) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.category_name)
                            )
                        },
                        isError = false
                    )
                    Row {
                        Button(
                            onClick = {
                                viewModel.onDialogHide()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.cancel))
                        }
                        Button(
                            onClick = {
                                viewModel.addCategory(homeDialogUiState.input)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.add))
                        }
                    }
                }
            }
        }
    }

    val homeUiState by viewModel.homeUiState.collectAsState()
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
                FloatingActionButton(onClick = {
                    viewModel.onDialogShow()
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_new_category)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        when (homeUiState) {
            is HomeUiState.Loading -> homeUiState as HomeUiState.Loading
            is HomeUiState.Success -> HomeSuccessScreen(
                categories = (homeUiState as HomeUiState.Success).categories,
                modifier = Modifier.padding(contentPadding),
                onSearchClicked = onSearchClicked,
                onCategoryClicked = onCategoryClicked
            )
            is HomeUiState.Error -> homeUiState as HomeUiState.Error
        }
    }
}

@Composable
fun HomeSuccessScreen(
    categories: List<CategoryWithImgUrls>,
    modifier: Modifier = Modifier,
    onSearchClicked: (String) -> Unit = {},
    onCategoryClicked: (String) -> Unit = {}
) {
    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                SearchBar(
                    placeholderText = stringResource(R.string.search),
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.clickable { onSearchClicked(GLOBAL) },
                    enabled = false
                )
            }
        }
        items(categories, key = { it.category.categoryId }) { category ->
            val categoryId = category.category.categoryId
            CategoryCard(
                title = categoryId,
                imgUrl = category.imgUrls.random().imgUrl,
                onClick = { onCategoryClicked(categoryId) }
            )
        }
    }
}


@Composable
fun CategoryCard(
    title: String,
    imgUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
fun HomeScreenPreview() {
    EldemLibTheme {
        HomeScreen()
    }
}

@Preview
@Composable
fun GroupCardPreview() {
    EldemLibTheme {
        CategoryCard(title = "Group", imgUrl = "")
    }
}