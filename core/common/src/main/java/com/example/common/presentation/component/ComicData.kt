package com.example.common.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.R
import com.example.common.presentation.theme.MarvelComposeTheme

@Composable
fun ComicData(
    title: String,
    thumbnail: String,
    url: String,
    modifier: Modifier = Modifier,
    onComicSelected: ((Int) -> Unit)? = null,
) {
    LoadImage(
        thumbnailUrl = thumbnail,
        contentScale = ContentScale.Fit,
        contentDescription = title,
        originalSize = true,
        noImage = R.drawable.not_load_image,
        modifier = modifier
            .then(
                if (onComicSelected != null) {
                    Modifier.clickable {
                        onComicSelected.invoke(getComicId(url))
                    }
                } else {
                    Modifier
                },
            )
            .height(150.dp)
            .padding(end = MarvelComposeTheme.paddings.medium)
            .clip(RoundedCornerShape(4.dp)),
    )
}

private fun getComicId(url: String) = url
    .split("/")
    .last()
    .toInt()

@Preview
@Composable
fun ComicDataPreview() {
    ComicData(title = "title", thumbnail = "thumbnailUrl", url = "url")
}
