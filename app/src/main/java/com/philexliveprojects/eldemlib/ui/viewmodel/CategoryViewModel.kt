package com.philexliveprojects.eldemlib.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import com.philexliveprojects.eldemlib.data.local.entity.Paragraph
import com.philexliveprojects.eldemlib.data.repository.ArticleRepository
import com.philexliveprojects.eldemlib.ui.CATEGORY_ID
import com.philexliveprojects.eldemlib.ui.TIMEOUT_MILLIS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ArticleRepository
) : ViewModel() {

    val category: String = checkNotNull(savedStateHandle[CATEGORY_ID])
    val articles = repository.getCategory("Person")
        .filterNotNull()
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = emptyList()
        )

    init {
        Log.e("CategoryViewModel", category)
    }

    fun addArticle(article: Article, paragraphs: List<Paragraph>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArticleWithParagraphs(ArticleWithParagraphs(article, paragraphs))
        }
}