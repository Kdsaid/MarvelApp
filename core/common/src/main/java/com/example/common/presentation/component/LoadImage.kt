package com.example.common.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.common.R

const val LoadImage = "loadImage"
const val Placeholder = "placeholder"

@Composable
fun LoadImage(
    thumbnailUrl: String,
    modifier: Modifier = Modifier,
    circular: Boolean = false,
    originalSize: Boolean = false,
    contentDescription: String? = null,
    @DrawableRes noImage: Int? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    if (thumbnailUrl.isNotEmpty()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(thumbnailUrl)
                .crossfade(enable = true)
                .placeholder(R.mipmap.ic_launcher)
                .apply {
                    if (circular) {
                        transformations(
                            CircleCropTransformation(),
                        )
                    }
                    if (originalSize) {
                        size(Size.ORIGINAL)
                    }
                }
                .build(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier.testTag(LoadImage),
        )
    } else if (noImage != null) {
        Image(
            painterResource(id = noImage),
            contentDescription = stringResource(id = R.string.no_image),
            modifier = modifier.testTag(Placeholder),
        )
    }
}

@Preview(widthDp = 150, heightDp = 150)
@Composable
fun LoadImagePreview() {
    LoadImage(thumbnailUrl = "", noImage = R.drawable.not_load_image)
}
