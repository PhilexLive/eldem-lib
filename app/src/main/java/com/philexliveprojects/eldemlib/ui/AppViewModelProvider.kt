package com.philexliveprojects.eldemlib.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.philexliveprojects.eldemlib.MainApplication
import com.philexliveprojects.eldemlib.ui.viewmodels.CategoryViewModel
import com.philexliveprojects.eldemlib.ui.viewmodels.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                application().container.articleRepository
            )
        }

        initializer {
            CategoryViewModel(
                createSavedStateHandle(),
                application().container.articleRepository
            )
        }
    }
}

private fun CreationExtras.application(): MainApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)