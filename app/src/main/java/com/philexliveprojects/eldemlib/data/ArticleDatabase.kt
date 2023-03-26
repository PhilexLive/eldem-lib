package com.philexliveprojects.eldemlib.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.philexliveprojects.eldemlib.data.local.converter.ArticleConverter
import com.philexliveprojects.eldemlib.data.local.dao.ArticleDao
import com.philexliveprojects.eldemlib.data.local.entity.*

@Database(entities = [Article::class, Paragraph::class], version = 2, exportSchema = false)
@TypeConverters(ArticleConverter::class)
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