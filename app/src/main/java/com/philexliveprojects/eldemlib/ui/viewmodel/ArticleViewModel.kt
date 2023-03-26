package com.philexliveprojects.eldemlib.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticleViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()
}