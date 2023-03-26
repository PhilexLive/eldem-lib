package com.philexliveprojects.eldemlib.data.local.entity

import androidx.room.*
import java.util.Date

// Represents an article of the app
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId: Long,
    val title: String,
    val description: String,
    val category: String,
    @kotlinx.serialization.Serializable
    val imgUrls: List<String>,
    val date: Date?
)

// Represents a paragraph of an article
@Entity
data class Paragraph(
    @PrimaryKey(autoGenerate = true)
    val paragraphId: Long,
    val articleId: Int,
    val position: Int,
    val title: String,
    val body: String
)

data class ArticleWithParagraphs(
    @Embedded
    val article: Article,
    @Relation(parentColumn = "articleId", entityColumn = "articleId")
    val paragraphs: List<Paragraph>
)

data class ArticleListItem(
    val articleId: Long,
    val title: String,
    val description: String
)
