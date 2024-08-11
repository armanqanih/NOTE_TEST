package org.lotka.xenonx.presentation.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lotka.xenonx.presentation.theme.CleanArchitectureNoteAppTheme
import org.lotka.xenonx.presentation.ui.app.HomeActivity
import org.lotka.xenonx.presentation.util.TestTag.PASSWORD_TOGGLE
import org.lotka.xenonx.presentation.util.TestTag.STANDARD_TEXT_FIELD

@RunWith(AndroidJUnit4::class)
class StandardTextFeildKTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    @Before
    fun setUp(){

    }

    @Test
    fun standardTextFeild_displayed() {
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
                    maxLength = 6,
                    modifier = Modifier.semantics {
                        testTag = STANDARD_TEXT_FIELD

                    }
                )

            }
        }




        val  expectedString = "aaaaa"
        composeTestRule.onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextClearance()

        composeTestRule.onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput(expectedString)

        composeTestRule.onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("a")

        composeTestRule.onNodeWithTag(STANDARD_TEXT_FIELD)
            .assertTextEquals(expectedString)

    }


    @Test
    fun enterPassword_toggleVisibility_passwordVisible() {
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
                    keyboardType = KeyboardType.Password,
                    maxLength = 6,
                    modifier = Modifier.semantics {
                        testTag = PASSWORD_TOGGLE

                    }
                )

            }
        }

        composeTestRule.onNodeWithTag(PASSWORD_TOGGLE)
            .performTextClearance()
        composeTestRule.onNodeWithTag(PASSWORD_TOGGLE)
            .performTextInput("aaaaa")




    }







}