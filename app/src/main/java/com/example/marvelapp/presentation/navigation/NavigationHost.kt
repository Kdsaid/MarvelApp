package com.example.marvelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.characters.presentation.navigation.charactersGraph
import com.example.common.presentation.deeplinks.CharactersScreen

const val NAVIGATION_HOST_TEST_TAG = "NavigationHost"

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController,
        startDestination = CharactersScreen,
        modifier = modifier.testTag(NAVIGATION_HOST_TEST_TAG)
    ) {
        charactersGraph(navController)
    }
}
