package com.philexliveprojects.eldemlib.data.local.daos

import androidx.room.*
import com.philexliveprojects.eldemlib.data.local.entities.Category
import com.philexliveprojects.eldemlib.data.local.entities.CategoryWithArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query(
        "SELECT * " +
                "FROM Category;"
    )
    fun getAllCategories(): Flow<List<Category>>

    @Transaction
    @Query(
        "SELECT * " +
                "FROM Category " +
                "WHERE categoryId = :categoryId;"
    )
    fun getCategoryWithArticles(categoryId: Long): Flow<CategoryWithArticles>
}