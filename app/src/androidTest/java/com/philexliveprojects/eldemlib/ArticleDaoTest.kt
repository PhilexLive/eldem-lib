package com.philexliveprojects.eldemlib

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.philexliveprojects.eldemlib.data.*
import com.philexliveprojects.eldemlib.data.article.ArticleDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
    private lateinit var articleDatabase: ArticleDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        articleDatabase = Room.inMemoryDatabaseBuilder(context, ArticleDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        articleDao = articleDatabase.articleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        articleDatabase.close()
    }

//    @Test
//    @Throws(IOException::class)
//    fun daoGetByGroup_getsArticlesInOneGroup() = runBlocking {
//        addAllItemsToDb()
//        val artistArticles = articleDao.getArticlesFromGroup("artist").first()
//        val expected = mockArticleList.filter { article -> article.groupId.id == "artist" }.reversed()
//        assertEquals(expected, artistArticles)
//    }
}