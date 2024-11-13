package com.example.characters.characters.presentation.screen

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.characters.characters.presentation.screen.detailsScreenRobot
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenScreenIsLoadedItShouldShowTheCharacterName() {
        detailsScreenRobot {
        } verify {
            characterIsDisplayed("character 1")
        }
    }

    @Test
    fun whenBackIsPressedItShouldInvokeBackPressed() {
        detailsScreenRobot {
            onBackPressed()
        } verify {
            backWasPressed()
        }
    }
}
