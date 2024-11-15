package com.example.characters.characters.presentation.screen

import androidx.annotation.StringRes
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.characters.characters.presentation.FakeCharacters
import com.example.characters.presentation.screen.DetailsScreenContent
import com.example.characters.presentation.viewmodel.state.DetailsState
import com.example.common.presentation.theme.MarvelComposeTheme
import com.example.common.R as commonR
import org.junit.Assert.assertTrue

fun DetailsScreenTest.detailsScreenRobot(
    func: DetailsScreenRobot.() -> Unit
) =
    DetailsScreenRobot(composeTestRule).apply { func() }

class DetailsScreenRobot(private val composeTestRule: ComposeContentTestRule) {
    private var backPressed = false

    init {
        composeTestRule.setContent {
            MarvelComposeTheme {
                DetailsScreenContent(
                    detailsState = DetailsState.Success(
                        character = FakeCharacters.getSampleCharacters(1).first(),
                    ),
                    onBackPressed = { backPressed = true },
                )
            }
        }
    }

    fun onBackPressed() {
        composeTestRule.onNodeWithContentDescription(getString(commonR.string.go_back)).performClick()
    }

    infix fun verify(func: DetailsScreenResult.() -> Unit) =
        DetailsScreenResult(composeTestRule, backPressed).apply { func() }
}

class DetailsScreenResult(
    private val composeTestRule: ComposeContentTestRule,
    private val backPressed: Boolean,
) {
    fun characterIsDisplayed(name: String) {
        textIsDisplayed(name)
    }

    fun backWasPressed() {
        assertTrue(backPressed)
    }

    @OptIn(ExperimentalTestApi::class)
    fun textIsDisplayed(text: String) {
        composeTestRule.waitUntilExactlyOneExists(hasText(text))
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }
}

private fun getString(@StringRes stringResource: Int) =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(stringResource)
