package com.philexliveprojects.eldemlib.data.image

import com.philexliveprojects.eldemlib.data.entity.ImgUrl
import kotlinx.coroutines.flow.Flow

class OfflineImageRepository(private val imageDao: ImageDao) : ImageRepository {
    override suspend fun insertImg(imgUrl: ImgUrl) = imageDao.insertImg(imgUrl)

    override suspend fun deleteImg(imgUrl: ImgUrl) = imageDao.deleteImg(imgUrl)
}