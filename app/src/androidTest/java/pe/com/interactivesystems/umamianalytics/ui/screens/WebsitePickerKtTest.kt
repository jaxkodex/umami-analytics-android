package pe.com.interactivesystems.umamianalytics.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.navigation.Screen
import pe.com.interactivesystems.umamianalytics.ui.screens.websites.WebsitePicker

@RunWith(AndroidJUnit4::class)
class WebsitePickerKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Test
    fun websitePicker() {
        val google = Website(id = "1", name = "Google", domain = "google.com")
        val websites = listOf(
            google,
            Website(id = "2", name = "Facebook", domain = "facebook.com")
        )

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavHost(navController = navController, startDestination = Screen.Websites.route) {
                composable(Screen.Websites.route) {
                    WebsitePicker(websites = websites, navController = navController)
                }
                composable(Screen.WebsiteHome.route) { }
            }
            navController.navigate(Screen.Websites.route)
        }

        composeTestRule.onNodeWithText("Google").performClick()

        assert(navController.currentBackStackEntry?.destination?.route == Screen.WebsiteHome.route)
    }
}