package com.example.characters.presentation.screen.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.characters.mockup_data.MockupData
import com.example.characters.presentation.component.CharactersPreview
import com.example.characters.presentation.viewmodel.state.HeroListErrorResult
import com.example.characters.presentation.viewmodel.state.HeroListNoResults
import com.example.characters.presentation.viewmodel.state.HeroListProgressIndicator
import com.example.characters.presentation.viewmodel.state.HeroListState
import com.example.characters.presentation.viewmodel.state.HeroListState.Error
import com.example.characters.presentation.viewmodel.state.HeroListState.Loading
import com.example.characters.presentation.viewmodel.state.HeroListState.Success
import com.example.characters.presentation.viewmodel.state.HeroListSuccessResult
import com.example.common.presentation.component.ErrorMessage
import com.example.common.presentation.component.Loading
import com.example.common.presentation.component.SimpleMessage
import com.example.common.presentation.theme.MarvelComposeTheme
import com.example.common.R as commonR

@Composable
fun HeroListState.BuildUI() {
    when (this) {
        is Loading -> BuildLoading()
        is Error -> BuildError()
        is Success -> BuildSuccess()
    }
}

@Composable
private fun BuildLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .testTag(HeroListProgressIndicator),
    ) {
        Loading()
    }
}

@Composable
private fun Error.BuildError() {
    val message = stringResource(id = commonR.string.error_data_message)
    ErrorMessage(
        message = message,
        modifier = Modifier.testTag(HeroListErrorResult),
        onRetry = retry,
    )
}

@Composable
private fun Success.BuildSuccess() {
    characters?.takeIf { it.isNotEmpty() }?.let {
        CharactersPreview(
            characters = characters,
            loadingMore = loadingMore,
            modifier = Modifier.testTag(HeroListSuccessResult),
            onClick = onClick,
            onEndReached = onEndReached,
        )
    } ?: run {
        SimpleMessage(
            message = stringResource(id = commonR.string.no_results),
            modifier = Modifier
                .fillMaxSize()
                .testTag(HeroListNoResults),
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewSearchSuccess() {
    MarvelComposeTheme {
        Success(listOf(MockupData.heroesSample.first())).BuildUI()
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewSearchError() {
    MarvelComposeTheme {
        Error(Exception("Whatever")).BuildUI()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchLoading() {
    MarvelComposeTheme {
        Loading
    }
}
