package com.philexliveprojects.eldemlib.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.philexliveprojects.eldemlib.EldemLibApplication
import com.philexliveprojects.eldemlib.ui.screen.*
import com.philexliveprojects.eldemlib.ui.viewmodel.ArticleViewModel
import com.philexliveprojects.eldemlib.ui.viewmodel.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                application().container.articleRepository
            )
        }
        initializer {
            ArticleViewModel(
                application().container.articleRepository
            )
        }
    }
}

fun CreationExtras.application(): EldemLibApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EldemLibApplication)