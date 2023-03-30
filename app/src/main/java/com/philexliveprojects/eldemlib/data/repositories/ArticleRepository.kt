package com.philexliveprojects.eldemlib.data.repositories

import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticlePreview
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import com.philexliveprojects.eldemlib.data.local.entities.Paragraph
import kotlinx.coroutines.flow.Flow

/**
 * Article repository interface
 */
interface ArticleRepository {
    suspend fun insertArticle(article: Article)
    suspend fun updateArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun getArticle(articleId: String): Flow<ArticleWithParagraphs>
}