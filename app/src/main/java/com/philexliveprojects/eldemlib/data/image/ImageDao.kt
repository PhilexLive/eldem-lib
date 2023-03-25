package com.philexliveprojects.eldemlib.data.image

import androidx.room.*
import com.philexliveprojects.eldemlib.data.entity.CategoryWithArticles
import com.philexliveprojects.eldemlib.data.entity.ImgUrl
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImg(imgUrl: ImgUrl)

    @Delete
    fun deleteImg(imgUrl: ImgUrl)
}