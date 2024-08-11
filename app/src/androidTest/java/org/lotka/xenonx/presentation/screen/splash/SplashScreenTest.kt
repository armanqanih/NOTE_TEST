package org.lotka.xenonx.presentation.screen.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lotka.xenonx.presentation.theme.CleanArchitectureNoteAppTheme
import org.lotka.xenonx.presentation.ui.app.HomeActivity
import org.lotka.xenonx.presentation.ui.navigation.ScreensNavigation
import org.lotka.xenonx.presentation.util.Constants.SPLASH_SCREEN_DURATION

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SplashScreenTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    @MockK
    lateinit var navController: NavController

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp(){
       MockKAnnotations.init(this)
    }



    @Test
    fun splashScreen_displayedAndDisappear() = testDispatcher.runBlockingTest {

        composeTestRule.setContent {
            CleanArchitectureNoteAppTheme {
              SplashScreen(
                  navController = navController
                 , dispatcher = testDispatcher
              )
            }
        }
        composeTestRule.onNodeWithContentDescription("Logo")
            .assertExists()

        testScheduler.apply { advanceTimeBy(3000L); runCurrent() }

        verify {
            navController.popBackStack()
            navController.navigate(ScreensNavigation.LoginScreen.route)

        }

    }


}