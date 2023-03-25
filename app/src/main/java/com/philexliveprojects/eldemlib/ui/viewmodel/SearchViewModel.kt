package com.philexliveprojects.eldemlib.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery

class SearchViewModel(
//    private val pieceRepository: PieceRepository
) : ViewModel() {
    val searchText = mutableStateOf("")

    fun getListByTags(tags: List<String>) = SimpleSQLiteQuery(
        "SELECT * FROM article WHERE ? IN tags",
        tags.toTypedArray()
    )

    fun clearSearchText() {
        searchText.value = ""
    }
}
