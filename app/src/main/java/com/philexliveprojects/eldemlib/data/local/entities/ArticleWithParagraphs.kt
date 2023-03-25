package com.philexliveprojects.eldemlib.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

// Relation between an article and paragraphs
data class ArticleWithParagraphs(
    @Embedded
    val article: Article,
    @Relation(parentColumn = "articleId", entityColumn = "paragraphId")
    val paragraphs: List<Paragraph>
)