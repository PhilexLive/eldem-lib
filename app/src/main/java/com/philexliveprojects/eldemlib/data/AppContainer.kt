package com.philexliveprojects.eldemlib.data

import android.content.Context
import com.philexliveprojects.eldemlib.data.repositories.ArticleRepository
import com.philexliveprojects.eldemlib.data.repositories.OfflineArticleRepository

interface AppContainer {
    val articleRepository: ArticleRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val articleRepository: ArticleRepository by lazy {
        OfflineArticleRepository(ArticleDatabase.getDatabase(context).articleDao())
    }
}