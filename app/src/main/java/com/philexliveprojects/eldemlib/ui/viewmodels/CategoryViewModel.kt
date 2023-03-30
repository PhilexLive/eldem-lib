package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.eldemlib.data.local.entities.Category
import com.philexliveprojects.eldemlib.data.local.entity.ArticlePreview
import com.philexliveprojects.eldemlib.data.repositories.CategoryRepository
import com.philexliveprojects.eldemlib.utilities.CATEGORY
import com.philexliveprojects.eldemlib.utilities.TIMEOUT_MILLIS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CategoryViewModel(
    savedStateHandle: SavedStateHandle,
    repository: CategoryRepository
) : ViewModel() {
    val uiState = repository.getCategoryWithArticles(savedStateHandle.get(CATEGORY) ?: -1)
        .map {
            CategoryUiState(
                category = it.category,
                articles = it.articles
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = CategoryUiState(Category(-1, ""), emptyList())
        )
}

data class CategoryUiState(
    val category: Category,
    val articles: List<ArticlePreview>
)