package com.example.common.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.R
import com.example.common.presentation.theme.MarvelComposeTheme

@Composable
fun SearchBar(
    state: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    hint: String? = null,
    requestFocus: Boolean = false,
    onValueChanged: (String) -> Unit = {},
    onLeadingClicked: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val searchTextContentDescription = stringResource(R.string.search_text)
    LaunchedEffect(requestFocus) {
        if (requestFocus) {
            focusRequester.requestFocus()
        }
    }
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            onValueChanged(value.text)
        },
        placeholder = {
            if (hint != null) {
                Text(
                    text = hint,
                    style = TextStyle(fontSize = 18.sp),
                )
            }
        },
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .semantics { contentDescription = searchTextContentDescription },
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            SearchBarLeadingIcon(onLeadingClicked)
        },
        trailingIcon = {
            if (state.value.text != "") {
                SearchBarTrailingIcon(state, onValueChanged)
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
        ),
    )
}

@Composable
private fun SearchBarLeadingIcon(onLeadingClicked: () -> Unit) {
    val closeContentDescription = stringResource(id = R.string.search_close)
    IconButton(
        onClick = onLeadingClicked,
        modifier = Modifier.semantics { contentDescription = closeContentDescription },
    ) {
        Icon(
            Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp),
        )
    }
}

@Composable
private fun SearchBarTrailingIcon(
    state: MutableState<TextFieldValue>,
    onValueChanged: (String) -> Unit,
) {
    val clearContentDescription = stringResource(id = R.string.search_clear)
    IconButton(
        onClick = {
            onCrossIconPressed(state, onValueChanged)
        },
        modifier = Modifier.semantics { contentDescription = clearContentDescription },
    ) {
        Icon(
            Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp),
        )
    }
}

private fun onCrossIconPressed(
    state: MutableState<TextFieldValue>,
    onValueChanged: (String) -> Unit,
) {
    state.value =
        TextFieldValue("")
    onValueChanged("")
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreviewEmpty() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    MarvelComposeTheme {
        SearchBar(textState)
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreviewWithText() {
    val textState = remember { mutableStateOf(TextFieldValue("IronMan")) }
    MarvelComposeTheme {
        SearchBar(textState)
    }
}
