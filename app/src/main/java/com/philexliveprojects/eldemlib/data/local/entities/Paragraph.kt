package com.philexliveprojects.eldemlib.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a paragraph of an article
@Entity
data class Paragraph(
    @PrimaryKey(autoGenerate = true)
    val paragraphId: Long,
    val articleId: Long,
    val position: Int,
    val title: String,
    val body: String
)