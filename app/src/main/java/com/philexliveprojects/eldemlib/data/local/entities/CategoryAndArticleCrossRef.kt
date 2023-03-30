package com.philexliveprojects.eldemlib.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.philexliveprojects.eldemlib.data.local.entity.Article
import com.philexliveprojects.eldemlib.data.local.entity.ArticlePreview

@Entity(primaryKeys = ["categoryId", "articleId"])
data class CategoryAndArticleCrossRef(
    val categoryId: Long,
    val articleId: Long
)

data class CategoryWithArticles(
    @Embedded
    val category: Category,
    @Relation(
        entity = Article::class,
        parentColumn = "categoryId",
        entityColumn = "articleId",
        associateBy = Junction(CategoryAndArticleCrossRef::class)
    )
    val articles: List<ArticlePreview>
)

data class ArticleWithCategories(
    @Embedded
    val article: Category,
    @Relation(
        parentColumn = "articleId",
        entityColumn = "categoryId",
        associateBy = Junction(CategoryAndArticleCrossRef::class)
    )
    val categories: List<Article>
)