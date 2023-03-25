package com.philexliveprojects.eldemlib.data.category

import com.philexliveprojects.eldemlib.data.entity.Category
import com.philexliveprojects.eldemlib.data.entity.CategoryAndImgUrlCrossRef
import com.philexliveprojects.eldemlib.data.entity.CategoryWithImgUrls
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineCategoryRepository(private val categoryDao: CategoryDao) : CategoryRepository {
    override suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category)

    override suspend fun insertCategoryAndImgUrl(categoryAndImgUrlCrossRef: CategoryAndImgUrlCrossRef) =
        categoryDao.insertCategoryAndImgUrl(categoryAndImgUrlCrossRef)

    override suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)

    override fun categoriesWithImgUrlsCount(): Flow<Int?> =
        categoryDao.getAllCategoriesWithImgUrlsCount()

    override fun getAllCategoriesWithImgUrls(): Flow<List<CategoryWithImgUrls>> =
        categoryDao.getAllCategoriesWithImgUrls()

    override fun getCategoryCount(): Flow<Int?> =
        categoryDao.categoryCount()

    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()
}