package com.philexliveprojects.eldemlib.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.philexliveprojects.eldemlib.data.article.ArticleDao
import com.philexliveprojects.eldemlib.data.category.CategoryDao
import com.philexliveprojects.eldemlib.data.entity.*
import com.philexliveprojects.eldemlib.data.image.ImageDao

@Database(
    entities = [
        Article::class,
        Category::class,
        Paragraph::class,
        ImgUrl::class,
        CategoryAndImgUrlCrossRef::class,
        ArticleImgUrlCrossRef::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun groupDao(): CategoryDao
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var Instance: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ArticleDatabase::class.java, "article_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}