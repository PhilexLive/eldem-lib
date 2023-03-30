package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.eldemlib.data.local.entities.Category
import com.philexliveprojects.eldemlib.data.local.entity.ArticlePreview
import com.philexliveprojects.eldemlib.data.repositories.ArticleRepository
import com.philexliveprojects.eldemlib.data.repositories.CategoryRepository
import com.philexliveprojects.eldemlib.utilities.TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: CategoryRepository
) : ViewModel() {
    val uiState = repository.getAllCategories()
        .map {
            HomeUiState(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState(emptyList())
        )
}

class HomeUiState(
    val categories: List<Category>
)