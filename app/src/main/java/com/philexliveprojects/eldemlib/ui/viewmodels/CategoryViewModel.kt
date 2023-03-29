package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository

class CategoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ArticleRepository
) : ViewModel() {

}