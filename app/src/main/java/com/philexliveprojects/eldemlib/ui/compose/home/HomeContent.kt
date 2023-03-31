package com.philexliveprojects.eldemlib.ui.compose.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.philexliveprojects.eldemlib.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCard(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onLongClick: (() -> Unit)? = null
) {
    Surface(
        modifier
            .height(200.dp)
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            )
    ) {
        /*TODO Implement image background*/

        Text(
            text = title,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            style = MaterialTheme.typography.h4
        )
    }
}


@Composable
fun CategoryCreationDialog(
    value: String,
    onDialogTextInput: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onAcceptRequest: () -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    show: Boolean = false
) {
    Box(modifier.fillMaxSize()) {
        if (show) {
            Dialog(onDismissRequest) {
                Column {
                    TextField(
                        value = value,
                        onValueChange = { onDialogTextInput(it) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.category_name)
                            )
                        },
                        isError = isError,
                        maxLines = 1
                    )
                    Row {
                        Button(
                            onClick = onDismissRequest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.cancel))
                        }
                        Button(
                            onClick = onAcceptRequest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = stringResource(R.string.add))
                        }
                    }
                }
            }
        }
    }
}
