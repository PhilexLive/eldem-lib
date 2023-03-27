package com.philexliveprojects.eldemlib.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository
import com.philexliveprojects.eldemlib.ui.TIMEOUT_MILLIS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {
    val recentArticles = articleRepository.getRecentArticles().map { it }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = emptyList()
    )

    val categories = articleRepository.getAllCategories().map { it }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = emptyList()
    )

    private val _tempCategories = MutableStateFlow(mutableListOf<String>())
    val tempCategories = _tempCategories.asStateFlow()

    fun deleteCategory(category: String) = viewModelScope.launch(Dispatchers.IO) {
        articleRepository.deleteCategory(category)
    }

    fun addTempCategory(category: String) = _tempCategories.value.add(category)

    fun deleteTempCategory(category: String) = _tempCategories.value.remove(category)
}
