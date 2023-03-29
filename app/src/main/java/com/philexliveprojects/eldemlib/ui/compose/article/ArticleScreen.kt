package com.philexliveprojects.eldemlib.ui.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.philexliveprojects.eldemlib.R
import com.philexliveprojects.eldemlib.ui.viewmodels.ArticleViewModel

/*
 * The name "Value" For this Screen means that this contain something undefined,
 * so the name have to be common for any one thing that represented on this screen.
 *
 * On this screen can be info only about one thing, event, fact, phenomenon, etc.
 */

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    editMode: Boolean = false,
    onImageClicked: (String) -> Unit = {}
) {
//    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                ArticleDescription(
                    description = "Some Description",
                    onDescriptionChange = { /*TODO*/ }
                )
            }
//            items(uiState) { piece ->
//                Piece(
//                    edit = editMode,
//                    titleValue = ,
//                    onTitleChange = { /*TODO*/ },
//                    bodyValue = ,
//                    onBodyChange = { /*TODO*/ }
//                )
//            }
//            item {
//                if (editMode) {
//                    /*TODO ValueScreen: Add AddPieceButton*/
//                }
//            }
        }
    }
}

@Composable
fun ArticleDescription(
    description: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    editMode: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ParagaphTextField(
            enabled = editMode,
            value = description,
            onValueChange = onDescriptionChange,
            textStyle = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(4.dp)
                .clearFocusOnKeyboardDismiss()
        ) { Text(text = stringResource(R.string.description)) }
    }
}

@Composable
fun EditParagraph(
    titleValue: String,
    bodyValue: String,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    edit: Boolean = true,
) {
    val focusManager = LocalFocusManager.current

    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(4.dp)) {
            ParagaphTextField(
                enabled = edit,
                value = titleValue,
                onValueChange = onTitleChange,
                maxLines = 4,
                textStyle = MaterialTheme.typography.h6,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier.clearFocusOnKeyboardDismiss()
            ) { Text(stringResource(R.string.title)) }

            ParagaphTextField(
                enabled = edit,
                value = bodyValue,
                onValueChange = onBodyChange,
                textStyle = MaterialTheme.typography.body1,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.clearFocusOnKeyboardDismiss()
            ) { Text(stringResource(R.string.body)) }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ParagaphTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isError: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null
) = BasicTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier.defaultMinSize(
        minWidth = TextFieldDefaults.MinWidth,
        minHeight = TextFieldDefaults.MinHeight
    ),
    textStyle = textStyle,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    maxLines = maxLines,
    decorationBox = { innerTextField ->
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = singleLine,
            placeholder = placeholder,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            isError = isError
        )
    }
)

@OptIn(ExperimentalLayoutApi::class)
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember { mutableStateOf(false) }
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (isFocused) {
        val imeIsVisible = WindowInsets.isImeVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}