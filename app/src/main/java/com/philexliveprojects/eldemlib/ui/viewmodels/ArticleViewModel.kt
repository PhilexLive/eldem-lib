package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.philexliveprojects.eldemlib.data.repositories.ArticleRepository

class ArticleViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

}