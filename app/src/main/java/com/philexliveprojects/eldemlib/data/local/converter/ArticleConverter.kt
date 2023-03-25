package com.philexliveprojects.eldemlib.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

class ArticleConverter {
    @TypeConverter
    fun convertListToJson(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    fun convertJsonToStringList(json: String): List<String> = Json.decodeFromString(json)

    @TypeConverter
    fun convertDateToTime(date: Date): Long = date.time

    @TypeConverter
    fun convertTimeToDate(time: Long): Date = Date(time)
}