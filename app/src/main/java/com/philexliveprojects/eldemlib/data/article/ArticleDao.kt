package com.philexliveprojects.eldemlib.data.article

import androidx.room.*
import com.philexliveprojects.eldemlib.data.entity.Article
import com.philexliveprojects.eldemlib.data.entity.CategoryWithArticles
import kotlinx.coroutines.flow.Flow

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

    @Transaction
    @Query(
        "SELECT * FROM Category " +
                "WHERE categoryId = :categoryId;"
    )
    fun getCategory(categoryId: String): Flow<CategoryWithArticles?>
}