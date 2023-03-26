package com.philexliveprojects.eldemlib.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoryScreen(
    groupId: String,
    modifier: Modifier = Modifier,
    onArticleClicked: (Int) -> Unit = {},
    onSearchClicked: (String) -> Unit = {}
) {
    /*TODO GroupScreen: content*/
}

@Composable
fun Article(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Text(text = title)
        Text(description)
        Divider()
    }
}