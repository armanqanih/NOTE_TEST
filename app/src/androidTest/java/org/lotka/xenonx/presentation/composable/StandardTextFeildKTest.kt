package org.lotka.xenonx.presentation.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lotka.xenonx.presentation.theme.CleanArchitectureNoteAppTheme
import org.lotka.xenonx.presentation.ui.app.HomeActivity

@RunWith(AndroidJUnit4::class)
class StandardTextFeildKTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            CleanArchitectureNoteAppTheme {
                var text by remember {
                    mutableStateOf("")
                }
                StandardTextField(
                    value = text,
                    onValueChange ={
                        text = it
                    },
                    maxLength = 6
                )

            }
        }
    }

    @Test
    fun standardTextFeild_displayed() {
        composeTestRule.onNodeWithTag("StandardTextField")
            .performTextInput("123456")
        composeTestRule.onNodeWithTag("StandardTextField")
            .assertTextEquals("12345")

    }
}