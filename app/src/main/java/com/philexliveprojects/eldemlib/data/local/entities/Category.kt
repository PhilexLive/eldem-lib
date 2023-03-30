package com.philexliveprojects.eldemlib.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["categoryName"], unique = true)])
data class Category(
    @PrimaryKey
    val categoryId: Long,
    val categoryName: String
)