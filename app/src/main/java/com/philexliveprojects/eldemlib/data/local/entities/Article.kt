package com.philexliveprojects.eldemlib.data.local.entities

import androidx.room.*

// Represents an article of the app
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId: Int,
    val name: String,
    val description: String,
    val category: String,
    val imgUrls: String
)

// Represents a paragraph of an article
@Entity
data class Paragraph(
    @PrimaryKey(autoGenerate = true)
    val paragraphId: Int,
    val articleRelation: Int,
    val position: Int,
    val title: String,
    val body: String
)

