package com.philexliveprojects.eldemlib.data.entity

import androidx.room.*

// Represents an article of the app
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId: Int,
    val name: String,
    val description: String,
    val categoryRelation: String
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

// Represent a category
@Entity
data class Category(
    @PrimaryKey val categoryId: String
)

// Represents an image url
@Entity
data class ImgUrl(
    @PrimaryKey val imgUrl: String
)

