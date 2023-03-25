package com.philexliveprojects.eldemlib.data.category

import androidx.room.*
import com.philexliveprojects.eldemlib.data.entity.Category
import com.philexliveprojects.eldemlib.data.entity.CategoryAndImgUrlCrossRef
import com.philexliveprojects.eldemlib.data.entity.CategoryWithImgUrls
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoryAndImgUrl(categoryAndImgUrlCrossRef: CategoryAndImgUrlCrossRef)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Transaction
    @Query("SELECT * FROM CategoryAndImgUrlCrossRef;")
    fun getAllCategoriesWithImgUrls(): Flow<List<CategoryWithImgUrls>>

    @Query("SELECT * FROM Category;")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT COUNT(*) FROM Category;")
    fun categoryCount(): Flow<Int?>

    @Query("SELECT COUNT(*) FROM CategoryAndImgUrlCrossRef;")
    fun getAllCategoriesWithImgUrlsCount(): Flow<Int?>
}