package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.philexliveprojects.eldemlib.ui.utilities.EldemLibRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class EldemLibViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EldemLibUiState())
    val uiState = _uiState.asStateFlow()

    fun goToCategory() = _uiState.update { it.copy(EldemLibRoute.Category) }

    fun goToArticle() = _uiState.update { it.copy(EldemLibRoute.Article) }

    fun resetUiState() {
        _uiState.value = EldemLibUiState()
    }
}

data class EldemLibUiState(
    val route: EldemLibRoute = EldemLibRoute.Home
)
