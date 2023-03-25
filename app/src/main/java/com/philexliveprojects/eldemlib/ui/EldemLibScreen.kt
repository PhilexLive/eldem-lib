package com.philexliveprojects.eldemlib.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.data.AppContainer
import com.philexliveprojects.eldemlib.ui.navigation.NavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EldemLibScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    var editMode by remember { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    ModalBottomSheetLayout(
        sheetContent = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = R.string.edit_mode))
                    Switch(checked = editMode, onCheckedChange = { editMode = !editMode })
                }
            }
        },
        sheetState = modalBottomSheetState
    ) {
        val coroutineScope = rememberCoroutineScope()

        NavGraph(
            modifier = modifier,
            navHostController = navHostController,
            onBottomSheetExpand = { coroutineScope.launch { modalBottomSheetState.show() } },
            editMode = editMode
        )
    }
}