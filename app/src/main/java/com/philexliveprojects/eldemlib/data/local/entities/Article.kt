package com.philexliveprojects.eldemlib.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.philexliveprojects.eldemlib.data.local.entities.Paragraph
import kotlinx.serialization.Serializable
import java.util.*

// Represents an article of the app
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId: Long,
    val title: String,
    val description: String,
    val category: String,
    @Serializable
    val imgUrls: List<String>,
    val date: Date?
)

// The preview of an article for lists
data class ArticlePreview(
    val articleId: Long,
    val title: String,
    val description: String,
    val imgUrls: List<String>
)

// Paragraphs and articles for article screen
data class ArticleWithParagraphs(
    @Embedded
    val article: Article,
    @Relation(parentColumn = "articleId", entityColumn = "articleId")
    val paragraphs: List<Paragraph>
)
