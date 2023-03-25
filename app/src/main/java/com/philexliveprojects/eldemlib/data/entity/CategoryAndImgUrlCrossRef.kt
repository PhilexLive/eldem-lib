package com.philexliveprojects.eldemlib.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["categoryId", "imgUrl"])
data class CategoryAndImgUrlCrossRef(
    val categoryId: String,
    val imgUrl: String
)

data class CategoryWithImgUrls(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "imgUrl",
        associateBy = Junction(CategoryAndImgUrlCrossRef::class)
    )
    val imgUrls: List<ImgUrl>
)