package com.philexliveprojects.eldemlib.data.article

import com.philexliveprojects.eldemlib.data.entity.Article

/**
 * Article repository interface
 */
interface ArticleRepository {
    suspend fun insertArticle(article: Article)

    suspend fun updateArticle(article: Article)

    suspend fun deleteArticle(article: Article)

//    fun getArticlesInGroup(groupId: String): Flow<GroupWithArticles>
//
//    fun getArticle(id: Int): Flow<ArticleWithWholeInfo?>
}