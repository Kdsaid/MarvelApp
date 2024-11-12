package com.example.characters.presentation.component

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.characters.R
import com.example.characters.domain.model.Character
import com.example.characters.domain.model.ComicSummary
import com.example.characters.domain.model.Link
import com.example.characters.mockup_data.MockupData
import com.example.characters.presentation.viewmodel.state.HeroListSuccessResult
import com.example.common.presentation.component.BackButton
import com.example.common.presentation.component.ComicData
import com.example.common.presentation.component.LoadImage
import com.example.common.presentation.component.SimpleMessage
import com.example.common.presentation.theme.MarvelComposeTheme
import timber.log.Timber
import com.example.common.R as commonR

internal const val COMIC_SHAPE_DISPLACEMENT = 10f

@Composable
fun HeroDetails(
    character: Character,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onComicSelected: (Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag(HeroListSuccessResult)
            .padding(bottom = MarvelComposeTheme.paddings.defaultPadding),
    ) {
        val avatarModifier = Modifier
            .fillMaxSize()
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HeroImage(character.thumbnail, character.name, avatarModifier)
                BackButton(onClick = { onBackPressed() })
            }
        }

        item {
            HeroName(character.name)
        }
        item {
            Description(character.description)
        }
        item {
            Box(
                Modifier.padding(horizontal = MarvelComposeTheme.paddings.defaultPadding)
                    .padding(bottom = MarvelComposeTheme.paddings.defaultPadding),
            ) {

                Comics(character.comics, onComicSelected)
            }
        }

        item {
            SectionTitle(stringResource(id = R.string.related_links))
        }
        character.links?.let { links ->
            items(links) {
                Urls(it)
            }
        }
    }

}


@Composable
fun HeroImage(
    thumbnail: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    LoadImage(
        thumbnailUrl = thumbnail,
        contentDescription = contentDescription,
        noImage = commonR.drawable.not_load_image,
        modifier = modifier,
    )
}

@Composable
private fun HeroName(name: String) {
    SectionTitle(stringResource(id = R.string.name))
    Text(
        text = name,
        color = MarvelComposeTheme.colors.primary,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MarvelComposeTheme.paddings.defaultPadding)
            .padding(bottom = MarvelComposeTheme.paddings.defaultPadding),
    )
}

@Composable
fun Urls(link: Link) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(link.url)) }
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp)

            .padding(horizontal = MarvelComposeTheme.paddings.defaultPadding)
            .padding(bottom = MarvelComposeTheme.paddings.defaultPadding)
            .clickable {
            context.startActivity(intent)
        },
        Arrangement.SpaceBetween
    ) {
        Text(
            text = link.type.uppercase(),
            modifier = Modifier.padding(
                vertical = 2.dp,
                horizontal = 8.dp,
            ),
        )

        Icon(
            modifier = Modifier.size(20.dp),
            contentDescription = "arrow_back",
            painter = painterResource(id = commonR.drawable.baseline_arrow_forward_ios_24),
        )
    }
}

@Composable
fun Description(description: String) {
    SectionTitle(stringResource(id = R.string.description))
    Text(
        modifier = Modifier.padding(horizontal = MarvelComposeTheme.paddings.defaultPadding)
            .padding(bottom = MarvelComposeTheme.paddings.defaultPadding),
        text = description.takeIf { it.isNotEmpty() }
            ?: stringResource(id = R.string.no_description),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun Comics(comics: List<ComicSummary>?, onComicSelected: (Int) -> Unit = {}) {
    SectionTitle(stringResource(id = R.string.comics))
    if (!comics.isNullOrEmpty()) {
        LazyRow {
            items(comics) { comic ->
                comic.thumbnail?.takeIf { it.isNotEmpty() }?.let { thumbnail ->
                    ComicData(
                        title = comic.title,
                        thumbnail = thumbnail,
                        url = comic.url,
                        onComicSelected = onComicSelected,
                    )
                    Timber.d("thumbnail: $thumbnail")
                }
            }
        }
    } else {
        SimpleMessage(
            message = stringResource(id = R.string.no_comics_info),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun SectionTitle(section: String) {
    Text(
        text = section,
        color = MarvelComposeTheme.colors.primary,
        style = MarvelComposeTheme.typography.titleMedium,
        modifier = Modifier.padding(
            horizontal = MarvelComposeTheme.paddings.medium,
            vertical = MarvelComposeTheme.paddings.medium
        ),
    )
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewHeroDetails() {
    MarvelComposeTheme {
        HeroDetails(MockupData.heroesSample.first())
    }
}
