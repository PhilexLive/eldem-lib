package com.philexliveprojects.eldemlib.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import com.philexliveprojects.eldemlib.data.local.entity.Paragraph
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository
import com.philexliveprojects.eldemlib.ui.CATEGORY_ID
import com.philexliveprojects.eldemlib.ui.TIMEOUT_MILLIS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ArticleRepository
) : ViewModel() {
    val articles = repository.getCategory(checkNotNull(savedStateHandle[CATEGORY_ID]))
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = emptyList()
        )

    fun addArticle(article: Article, paragraphs: List<Paragraph>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArticleWithParagraphs(ArticleWithParagraphs(article, paragraphs))
        }
}