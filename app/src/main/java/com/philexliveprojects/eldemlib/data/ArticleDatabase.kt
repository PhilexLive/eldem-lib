package com.philexliveprojects.eldemlib.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.philexliveprojects.eldemlib.data.local.dao.ArticleDao
import com.philexliveprojects.eldemlib.data.local.dao.CategoryDao
import com.philexliveprojects.eldemlib.data.local.entities.*

@Database(
    entities = [
        Article::class,
        Paragraph::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

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