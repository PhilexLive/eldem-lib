package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.philexliveprojects.eldemlib.utilities.ContentType
import com.philexliveprojects.eldemlib.utilities.EldemLibRoute
import com.philexliveprojects.eldemlib.ui.viewmodels.EldemLibUiState

@Composable
fun EldemLibAppContent(
    contentType: ContentType,
    uiState: EldemLibUiState,
    modifier: Modifier = Modifier,
    navigateTo: (EldemLibRoute) -> Unit = {},
    navigateBack: (EldemLibRoute?) -> Unit = {}
) {
    when (contentType) {
        ContentType.HomeAndDetail -> {
            Column(modifier = modifier) {
                Scaffold(
                    topBar = {
                        TODO("Instant search appBar")
                    }
                ) { contentPadding ->
                    if (uiState.route == EldemLibRoute.Home) {
                        TODO("Instant Home Route")
                    } else {
                        TODO("Instant Category Screen")
                    }
                }
                TODO("Instant Article Screen")
            }
        }
        ContentType.Home -> {
            when (uiState.route) {
                EldemLibRoute.Home -> TODO("Add Home Screen")
                EldemLibRoute.Category -> TODO("Add Category Screen")
                EldemLibRoute.Article -> TODO("Add Article Screen")
            }
        }
    }
}