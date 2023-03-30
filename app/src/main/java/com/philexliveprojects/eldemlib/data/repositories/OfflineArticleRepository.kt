package com.philexliveprojects.eldemlib.data.repositories

import com.philexliveprojects.eldemlib.data.local.daos.ArticleDao
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import kotlinx.coroutines.flow.Flow

class OfflineArticleRepository(private val articleDao: ArticleDao) : ArticleRepository {
    override suspend fun insertArticle(article: Article) = articleDao.insertArticle(article)
    override suspend fun updateArticle(article: Article) = articleDao.updateArticle(article)
    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)
    override fun getArticle(articleId: String): Flow<ArticleWithParagraphs> =
        articleDao.getArticle(articleId)
}