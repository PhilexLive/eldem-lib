package com.philexliveprojects.eldemlib.data.entity

import androidx.room.*

// Nested Relationship between article
@Entity(primaryKeys = ["articleId", "imgUrl"])
data class ArticleImgUrlCrossRef(
    val articleId: Int,
    val imgUrl: String
)

// Relation between an article and image urls
data class ArticleWithImgUrls(
    @Embedded val article: Article,
    @Relation(
        parentColumn = "articleId",
        entityColumn = "imgUrl",
        associateBy = Junction(ArticleImgUrlCrossRef::class)
    )
    val imgUrl: List<ImgUrl>
)

// Relation between an image url and articles
data class ImgUrlWithArticles(
    @Embedded val imgUrl: ImgUrl,
    @Relation(
        parentColumn = "imgUrl",
        entityColumn = "articleId",
        associateBy = Junction(ArticleImgUrlCrossRef::class)
    )
    val articles: List<Article>
)
