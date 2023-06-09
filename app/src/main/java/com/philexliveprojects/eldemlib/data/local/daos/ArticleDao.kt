package com.philexliveprojects.eldemlib.data.local.daos

import androidx.room.*
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: Article)

    @Update
    suspend fun updateArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Transaction
    @Query(
        "SELECT * " +
                "FROM Article " +
                "WHERE articleId = :articleId;"
    )
    fun getArticle(articleId: String): Flow<ArticleWithParagraphs>
}