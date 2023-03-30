package com.philexliveprojects.eldemlib.data.repositories

import com.philexliveprojects.eldemlib.data.local.daos.CategoryDao
import com.philexliveprojects.eldemlib.data.local.entities.Category
import com.philexliveprojects.eldemlib.data.local.entities.CategoryWithArticles
import kotlinx.coroutines.flow.Flow

class OfflineCategoryRepository(private val categoryDao: CategoryDao) : CategoryRepository {
    override suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category)

    override suspend fun updateCategory(category: Category) = categoryDao.updateCategory(category)

    override suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)

    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()

    override fun getCategoryWithArticles(categoryId: Long): Flow<CategoryWithArticles> =
        categoryDao.getCategoryWithArticles(categoryId)

}