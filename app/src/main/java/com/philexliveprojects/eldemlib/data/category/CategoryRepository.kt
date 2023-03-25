package com.philexliveprojects.eldemlib.data.category

import com.philexliveprojects.eldemlib.data.entity.Category
import com.philexliveprojects.eldemlib.data.entity.CategoryAndImgUrlCrossRef
import com.philexliveprojects.eldemlib.data.entity.CategoryWithImgUrls
import kotlinx.coroutines.flow.Flow

/**
 * Group repository interface
 */
interface CategoryRepository {
    suspend fun insertCategory(category: Category)

    suspend fun insertCategoryAndImgUrl(categoryAndImgUrlCrossRef: CategoryAndImgUrlCrossRef)

    suspend fun deleteCategory(category: Category)

    fun categoriesWithImgUrlsCount(): Flow<Int?>

    fun getAllCategoriesWithImgUrls(): Flow<List<CategoryWithImgUrls>>

    fun getCategoryCount(): Flow<Int?>

    fun getAllCategories(): Flow<List<Category>>
}