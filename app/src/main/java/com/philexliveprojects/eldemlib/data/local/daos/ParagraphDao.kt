package com.philexliveprojects.eldemlib.data.local.daos

import androidx.room.*
import com.philexliveprojects.eldemlib.data.local.entities.Paragraph
import kotlinx.coroutines.flow.Flow

@Dao
interface ParagraphDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertParagraph(paragraphs: Paragraph)

    @Update
    suspend fun updateParagraph(paragraph: Paragraph)

    @Delete
    suspend fun deleteParagraph(deleteParagraph: Paragraph)

    @Query(
        "SELECT * " +
                "FROM paragraph " +
                "WHERE articleId = :articleId"
    )
    suspend fun getParagraphsInArticle(articleId: Long): Flow<List<Paragraph>>
}