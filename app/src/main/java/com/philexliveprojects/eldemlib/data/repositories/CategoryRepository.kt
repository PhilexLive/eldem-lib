package com.philexliveprojects.eldemlib.data.repositories

import com.philexliveprojects.eldemlib.data.local.entities.Category
import com.philexliveprojects.eldemlib.data.local.entities.CategoryWithArticles
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: Category)
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryWithArticles(categoryId: Long): Flow<CategoryWithArticles>
}