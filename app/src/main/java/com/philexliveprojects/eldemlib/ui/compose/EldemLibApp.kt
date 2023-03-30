package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.philexliveprojects.eldemlib.utilities.ContentType
import com.philexliveprojects.eldemlib.ui.viewmodels.EldemLibViewModel

@Composable
fun EldemLibApp(windowWidthSizeClass: WindowWidthSizeClass) {
    val contentType = when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> ContentType.Home
        WindowWidthSizeClass.Medium -> ContentType.Home
        WindowWidthSizeClass.Expanded -> ContentType.HomeAndDetail
        else -> ContentType.Home
    }
    val viewModel = hiltViewModel<EldemLibViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    EldemLibAppContent(
        contentType = contentType,
        uiState = uiState
    )
}