package com.example.characters.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.characters.presentation.screen.DetailsScreen
import com.example.characters.presentation.screen.HeroListScreen
import com.example.characters.presentation.viewmodel.DetailsViewModel
import com.example.characters.presentation.viewmodel.HeroListViewModel
import com.example.common.presentation.deeplinks.CharacterDetails
import com.example.common.presentation.deeplinks.CharactersScreen
import com.example.common.presentation.deeplinks.BASE_PATH


fun NavGraphBuilder.charactersGraph(navController: NavHostController) {
    composable<CharactersScreen>(
        deepLinks = listOf(navDeepLink<CharactersScreen>(basePath = "$BASE_PATH/characters")),
    ) { navBackStackEntry ->
        val viewModel: HeroListViewModel = hiltViewModel(navBackStackEntry)
        HeroListScreen(
            viewModel = viewModel,
            onCharacterSelected = { selectedCharacter ->
                viewModel.resetSelectedCharacter()
                navController.navigate(CharacterDetails(selectedCharacter.id))
            },
        )
    }
    composable<CharacterDetails>(
        deepLinks = listOf(navDeepLink<CharacterDetails>(basePath = "$BASE_PATH/characters")),
    ) { navBackStackEntry ->
        val viewModel: DetailsViewModel = hiltViewModel(navBackStackEntry)
        DetailsScreen(
            viewModel = viewModel,
            onBackPressed = {
                navController.popBackStack(
                    route = CharactersScreen,
                    inclusive = false,
                )
            },
            onComicSelected = { comicId ->
            },
        )
    }
}
