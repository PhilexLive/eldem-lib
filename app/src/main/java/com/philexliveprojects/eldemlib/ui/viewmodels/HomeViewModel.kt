package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository


class HomeViewModel(
    private val repository: ArticleRepository
) : ViewModel() {

}
