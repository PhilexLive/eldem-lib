package com.philexliveprojects.eldemlib

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.philexliveprojects.eldemlib.data.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
//    private lateinit var articleDatabase: ArticleDatabase
//    private lateinit var articleDao: ArticleDao
//
//    @Before
//    fun createDb() {
//        val context: Context = ApplicationProvider.getApplicationContext()
//
//        articleDatabase = Room.inMemoryDatabaseBuilder(context, ArticleDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//        articleDao = articleDatabase.articleDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        articleDatabase.close()
//    }

//    @Test
//    @Throws(IOException::class)
//    fun daoGetByGroup_getsArticlesInOneGroup() = runBlocking {
//        addAllItemsToDb()
//        val artistArticles = articleDao.getArticlesFromGroup("artist").first()
//        val expected = mockArticleList.filter { article -> article.groupId.id == "artist" }.reversed()
//        assertEquals(expected, artistArticles)
//    }
}