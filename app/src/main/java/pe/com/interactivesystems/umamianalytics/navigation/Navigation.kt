package pe.com.interactivesystems.umamianalytics.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.com.interactivesystems.umamianalytics.domain.repository.WebsitePickerViewModel
import pe.com.interactivesystems.umamianalytics.ui.screens.websites.WebsitePicker

sealed class Screen(val route: String) {
    data object Websites : Screen("websites")
    data object WebsiteHome : Screen("websites/{websiteId}") {
        fun build(websiteId: String) = "websites/$websiteId"
    }
}

@Composable
fun ApplicationNavigation(
    navController: NavHostController,
    websitePickerViewModel: WebsitePickerViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Websites.route) {
        composable(Screen.Websites.route) {
            WebsitePicker(
                websitePickerViewModel = websitePickerViewModel,
                navController = navController
            )
        }
    }
}