package com.philexliveprojects.eldemlib.data.entity

import androidx.room.Embedded
import androidx.room.Relation

// Relation between a group and articles
data class CategoryWithArticles(
    @Embedded val category: Category,
    @Relation(parentColumn = "categoryId", entityColumn = "categoryRelation")
    val articles: List<Article>
)