package com.philexliveprojects.eldemlib.data.article

import com.philexliveprojects.eldemlib.data.entity.Article

class OfflineArticleRepository(private val articleDao: ArticleDao) : ArticleRepository {
    override suspend fun insertArticle(article: Article) = articleDao.insertArticle(article)

    override suspend fun updateArticle(article: Article) = articleDao.updateArticle(article)

    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

//    override fun getArticlesInGroup(groupId: String): Flow<GroupWithArticles> =
//        articleDao.getArticlesInGroup(groupId)
//
//    override fun getArticle(id: Int): Flow<ArticleWithWholeInfo?> = articleDao.getArticle(id)
}