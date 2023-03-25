package com.philexliveprojects.eldemlib.data

import android.content.Context
import com.philexliveprojects.eldemlib.data.article.ArticleRepository
import com.philexliveprojects.eldemlib.data.article.OfflineArticleRepository
import com.philexliveprojects.eldemlib.data.category.CategoryRepository
import com.philexliveprojects.eldemlib.data.category.OfflineCategoryRepository
import com.philexliveprojects.eldemlib.data.image.ImageRepository
import com.philexliveprojects.eldemlib.data.image.OfflineImageRepository

interface AppContainer {
    val articleRepository: ArticleRepository
    val categoryRepository: CategoryRepository
    val imageRepository: ImageRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val articleRepository: ArticleRepository by lazy {
        OfflineArticleRepository(ArticleDatabase.getDatabase(context).articleDao())
    }

    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(ArticleDatabase.getDatabase(context).groupDao())
    }

    override val imageRepository: ImageRepository by lazy {
        OfflineImageRepository(ArticleDatabase.getDatabase(context).imageDao())
    }
}