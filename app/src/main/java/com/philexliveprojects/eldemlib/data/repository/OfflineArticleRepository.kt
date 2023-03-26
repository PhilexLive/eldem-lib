package com.philexliveprojects.eldemlib.data.repository

import com.philexliveprojects.eldemlib.data.local.dao.ArticleDao
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import kotlinx.coroutines.flow.Flow

class OfflineArticleRepository(private val articleDao: ArticleDao) : ArticleRepository {
    override suspend fun insertArticle(article: Article) = articleDao.insertArticle(article)

    override suspend fun updateArticle(article: Article) = articleDao.updateArticle(article)

    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

    override suspend fun deleteCategory(category: String) = articleDao.deleteCategory(category)

    override fun getAllCategories(): Flow<List<String>> = articleDao.getAllCategories()

    override fun getCategory(category: String): Flow<List<ArticleListItem>> =
        articleDao.getCategory(category)

    override fun getArticle(articleId: String): Flow<ArticleWithParagraphs> =
        articleDao.getArticle(articleId)

    override fun getRecentArticles(): Flow<List<ArticleListItem>> = articleDao.getRecentArticles()
}