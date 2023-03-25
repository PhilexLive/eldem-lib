package com.philexliveprojects.eldemlib.data.local.dao

import androidx.room.*
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticleListItem
import com.philexliveprojects.eldemlib.data.local.entity.ArticleWithParagraphs
import kotlinx.coroutines.flow.Flow

private const val LIMIT = 5
private const val OFFSET = 0

@Dao
interface ArticleDao {
    /*
     * Article access
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: Article)

    @Update
    suspend fun updateArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query(
        "DELETE FROM Article " +
                "WHERE category = :category;"
    )
    suspend fun deleteCategory(category: String)

    @Query("SELECT DISTINCT category FROM Article;")
    fun getAllCategories(): Flow<List<String>>

    @Query(
        "SELECT " +
                "articleId AS articleId, " +
                "title AS title, " +
                "description AS description " +
                "FROM Article " +
                "WHERE category = :category;"
    )
    fun getCategory(category: String): Flow<List<ArticleListItem>>

    @Transaction
    @Query(
        "SELECT * " +
                "FROM Article " +
                "WHERE articleId = :articleId;"
    )
    fun getArticle(articleId: String): Flow<ArticleWithParagraphs>

    @Query(
        "SELECT " +
                "articleId AS articleId, " +
                "title AS title, " +
                "description AS description " +
                "FROM Article " +
                "ORDER BY date DESC " +
                "LIMIT $LIMIT OFFSET $OFFSET;"
    )
    fun getRecent(): Flow<List<ArticleListItem>>
}