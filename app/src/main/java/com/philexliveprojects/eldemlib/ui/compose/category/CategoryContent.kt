package com.philexliveprojects.eldemlib.ui.compose.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.philexliveprojects.eldemlib.data.local.entity.ArticlePreview
import com.philexliveprojects.eldemlib.ui.theme.EldemLibTheme

@Composable
fun ArticlePreview(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium
    ) {
        /*TODO Implement Image background*/
        Column(Modifier.padding(8.dp)) {
            Text(text = title, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = description, fontWeight = FontWeight.Light)
        }
    }
}

@Preview
@Composable
fun CategoryListItemPreview() {
    EldemLibTheme(darkTheme = false) {
        ArticlePreview("Subanutica", "A survival video game")
    }
}