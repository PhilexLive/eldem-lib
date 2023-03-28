package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.philexliveprojects.eldemlib.ui.utilities.ContentType
import com.philexliveprojects.eldemlib.ui.viewmodels.EldemLibViewModel

@Composable
fun EldemLibApp(windowWidthSizeClass: WindowWidthSizeClass) {
    val contentType = when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> ContentType.ListOnly
        WindowWidthSizeClass.Medium -> ContentType.ListAndDetail
        WindowWidthSizeClass.Expanded -> ContentType.MultipleListsAndDetail
        else -> ContentType.ListOnly
    }
    val viewModel = hiltViewModel<EldemLibViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        contentType = contentType,
        uiState = uiState
    )
}