package com.example.characters.presentation.screen.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.characters.mockup_data.MockupData
import com.example.characters.presentation.component.HeroDetails
import com.example.characters.presentation.viewmodel.state.DetailsState
import com.example.characters.presentation.viewmodel.state.DetailsState.Loading
import com.example.characters.presentation.viewmodel.state.DetailsState.Success
import com.example.characters.presentation.viewmodel.state.HeroListProgressIndicator
import com.example.common.presentation.component.Loading
import com.example.common.presentation.theme.MarvelComposeTheme

@Composable
fun DetailsState.BuildUI(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onComicSelected: (Int) -> Unit,

    ) {
    when (this) {
        is Loading -> BuildLoading()
        is Success -> HeroDetails(
            character = character,
            modifier = modifier,
            onBackPressed = onBackPressed,
            onComicSelected = onComicSelected,
        )
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

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewDetailsSuccess() {
    MarvelComposeTheme {
        Success(MockupData.heroesSample.first()).BuildUI {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsLoading() {
    MarvelComposeTheme {
        Loading
    }
}
