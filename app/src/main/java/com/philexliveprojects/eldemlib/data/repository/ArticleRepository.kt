package com.philexliveprojects.eldemlib.data.repository

import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import kotlinx.coroutines.flow.Flow

/**
 * Article repository interface
 */
interface ArticleRepository {
    suspend fun insertArticle(article: Article)

    suspend fun updateArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    suspend fun deleteCategory(category: String)

    fun getAllCategories(): Flow<List<String>>

    fun getCategory(category: String): Flow<List<ArticleListItem>>

    fun getArticle(articleId: String): Flow<ArticleWithParagraphs>

    fun getRecent(): Flow<List<ArticleListItem>>
}