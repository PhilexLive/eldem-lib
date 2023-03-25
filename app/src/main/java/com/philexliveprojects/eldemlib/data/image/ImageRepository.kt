package com.philexliveprojects.eldemlib.data.image

import com.philexliveprojects.eldemlib.data.entity.ImgUrl
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun insertImg(imgUrl: ImgUrl)

    suspend fun deleteImg(imgUrl: ImgUrl)
}