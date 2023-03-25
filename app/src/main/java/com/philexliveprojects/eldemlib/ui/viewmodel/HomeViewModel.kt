package com.philexliveprojects.eldemlib.ui.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    object Loading : HomeUiState

    @Stable
    class Success(
        val recent: List<ArticleListItem>?,
        var categories: List<String>?
    ) : HomeUiState {
        fun addTempCategory(category: String) {
            TODO("HomeUiState.Success: implement adding temp category.")
        }
    }
}

class HomeViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        initializeHomeUiState()
    }

    /* Initialization/reinitialization function for HomeUiState. */
    private fun initializeHomeUiState() {
        homeUiState = HomeUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            var recent: List<ArticleListItem>? = null
            var categories: List<String>? = null

            articleRepository.getRecent().collect { recent = it }
            articleRepository.getAllCategories().collect { categories = it }

            homeUiState = HomeUiState.Success(
                recent = recent,
                categories = categories
            )
        }
    }

    fun deleteCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        articleRepository.deleteCategory(category)
    }


}
