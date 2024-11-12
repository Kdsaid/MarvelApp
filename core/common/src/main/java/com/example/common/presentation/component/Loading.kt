package com.example.common.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.common.R
import com.example.common.presentation.theme.LocalLottieAnimationIterations

@Composable
fun Loading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LocalLottieAnimationIterations.current.iterations,
    )

    LottieAnimation(
        composition,
        { progress },
        modifier = Modifier.size(100.dp),
    )
}
