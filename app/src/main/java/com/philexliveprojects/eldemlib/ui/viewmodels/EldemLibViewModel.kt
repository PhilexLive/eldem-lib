package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.philexliveprojects.eldemlib.MainApplication
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EldemLibViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ArticleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EldemLibUiState())
    val uiState = _uiState.asStateFlow()

    companion object {
        fun CreationExtras.application() = this[APPLICATION_KEY] as MainApplication
        val Factory = viewModelFactory {
            initializer {
                EldemLibViewModel(
                    savedStateHandle = createSavedStateHandle(),
                    repository = application().container.articleRepository
                )
            }
        }
    }
}

@Stable
class EldemLibUiState(

) {

}