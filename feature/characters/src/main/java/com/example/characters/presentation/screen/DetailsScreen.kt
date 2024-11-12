package com.example.characters.presentation.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.characters.mockup_data.MockupData
import com.example.characters.presentation.screen.state.BuildUI
import com.example.characters.presentation.viewmodel.DetailsViewModel
import com.example.characters.presentation.viewmodel.state.DetailsState
import com.example.common.presentation.theme.MarvelComposeTheme

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackPressed: () -> Unit,
    onComicSelected: (Int) -> Unit,
) {
    val screenState by viewModel.detailsState.collectAsStateWithLifecycle()
    DetailsScreenContent(screenState, onBackPressed, onComicSelected)
}

@Composable
internal fun DetailsScreenContent(
    detailsState: DetailsState,
    onBackPressed: () -> Unit = {},
    onComicSelected: (Int) -> Unit = {},
) {
    Scaffold { padding ->
        detailsState.BuildUI(
            modifier = Modifier.padding(padding),
            onBackPressed = onBackPressed,
            onComicSelected = onComicSelected
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun DetailsPreviewSuccess() {
    MarvelComposeTheme {
        DetailsScreenContent(DetailsState.Success(MockupData.heroesSample.first()))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPreviewLoading() {
    MarvelComposeTheme {
        DetailsScreenContent(DetailsState.Loading)
    }
}

@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 1440,
    heightDp = 720,
)
@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 1440,
    heightDp = 720,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun DetailsPreviewSuccessLandscape() {
    MarvelComposeTheme {
        DetailsScreenContent(DetailsState.Success(MockupData.heroesSample.first()))
    }
}
