package com.philexliveprojects.eldemlib.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.philexliveprojects.eldemlib.EldemLibApplication
import com.philexliveprojects.eldemlib.ui.screen.*

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                application().container.categoryRepository
            )
        }
//        initializer {
//            GroupViewModel(EldemLibApplication().container.pieceRepository)
//        }
//        initializer {
//            EntityViewModel(EldemLibApplication().container.pieceRepository)
//        }
//        initializer {
//            SearchViewModel(EldemLibApplication().container.pieceRepository)
//        }
    }
}

fun CreationExtras.application(): EldemLibApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EldemLibApplication)