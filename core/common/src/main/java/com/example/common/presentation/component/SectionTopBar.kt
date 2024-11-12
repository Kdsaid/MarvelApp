package com.example.common.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.R
import com.example.common.presentation.theme.MarvelComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionTopBar(
    icon: ImageVector? = null,
    onIconClick: () -> Unit = {},
) {

    TopAppBar(
        title = {
            Box(
                modifier =Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_marvel_logo),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .size(width = 300.dp, height = 80.dp)
                )
            }
                },
        actions = {
            if (icon != null) {
                IconButton(
                    onClick = onIconClick,
                    modifier = Modifier.size(width = 80.dp, height = 80.dp)
                ) {

                    Icon(
                        imageVector = icon,
                        tint = Color.White,
                        contentDescription = stringResource(id = R.string.search),
                        modifier = Modifier
                            .padding(MarvelComposeTheme.paddings.medium)
                            .size(75.dp)
                    )
                }
            }
        },
        modifier = Modifier.height(70.dp),
    )
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun SectionTabBarPreview() {
    MarvelComposeTheme {
        SectionTopBar()
    }
}
