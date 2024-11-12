package com.example.characters.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.characters.domain.model.Character
import com.example.characters.mockup_data.MockupData
import com.example.characters.presentation.component.semantics.numColumns
import com.example.common.presentation.component.LoadImage
import com.example.common.presentation.theme.CustomDiamond
import com.example.common.presentation.theme.MarvelComposeTheme
import timber.log.Timber
import com.example.common.R as commonR

private const val EXPANDED_SCREEN_WIDTH = 800
private const val MEDIUM_SCREEN_WIDTH = 600
private const val EXPANDED_SCREEN_COLUMNS = 3
private const val MEDIUM_SCREEN_COLUMNS = 2
private const val COMPACT_SCREEN_COLUMNS = 1

@Composable
fun CharactersPreview(
    characters: List<Character>,
    modifier: Modifier = Modifier,
    loadingMore: Boolean = false,
    onClick: (Character) -> Unit = {},
    onEndReached: () -> Unit = {},
) {
    val columns = getDeviceColumns()
    val lastIndex = characters.lastIndex
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier
            .fillMaxSize()
            .semantics { numColumns = columns },
    ) {
        itemsIndexed(characters) { index, hero ->
            if (lastIndex == index) {
                LaunchedEffect(Unit) {
                    onLastElementReached(onEndReached)
                }
            }
            HeroResult(hero, onClick)
        }
        if (loadingMore) {
            item {
                Loading()
            }
        }
    }
}

@Composable
private fun getDeviceColumns(): Int {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    return if (screenWidth >= EXPANDED_SCREEN_WIDTH) {
        EXPANDED_SCREEN_COLUMNS
    } else if (screenWidth >= MEDIUM_SCREEN_WIDTH) {
        MEDIUM_SCREEN_COLUMNS
    } else {
        COMPACT_SCREEN_COLUMNS
    }
}

private fun onLastElementReached(onEndReached: () -> Unit) {
    Timber.d("End element reached")
    onEndReached.invoke()
}

@Composable
private fun HeroResult(
    character: Character,
    onClick: (Character) -> Unit = {},
) {
    Row {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable(
                    onClickLabel = stringResource(id = commonR.string.show_details)
                ) {
                    onClick(character)
                }
        ) {
            HeroImage(
                character = character,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                contentAlignment = Alignment.BottomStart


            ) {
                HeroName(character = character)
            }
        }
    }

}

@Composable
private fun HeroImage(
    character: Character,
    modifier: Modifier,
) {
    if (character.thumbnail.isNotEmpty()) {
        RemoteImage(character, modifier)
    } else {
        LocalImage(character, modifier)
    }
}

@Composable
private fun RemoteImage(
    character: Character,
    modifier: Modifier,
) {
    LoadImage(
        thumbnailUrl = character.thumbnail,
        contentDescription = character.name,
        modifier = modifier,
    )
}

@Composable
private fun LocalImage(
    character: Character,
    modifier: Modifier,
) {
    Image(
        painterResource(id = commonR.drawable.not_load_image),
        contentDescription = character.name,
        modifier = modifier,
    )
}

@Composable
private fun HeroName(character: Character) {

        Column(
            modifier = Modifier.width(150.dp).height(50.dp).background(
                shape = CustomDiamond(COMIC_SHAPE_DISPLACEMENT),
                color = MarvelComposeTheme.colors.background
            ), verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = character.name,
                color = MarvelComposeTheme.colors.onBackground,
                style = MarvelComposeTheme.typography.bodyLarge,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
            )
        }

}

@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun HeroResultPreview() {
    val heroes = remember { listOf(MockupData.heroesSample.first()) }
    MarvelComposeTheme {
        CharactersPreview(heroes)
    }
}
