package pe.com.interactivesystems.umamianalytics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import pe.com.interactivesystems.umamianalytics.domain.repository.WebsitePickerViewModel
import pe.com.interactivesystems.umamianalytics.navigation.ApplicationNavigation
import pe.com.interactivesystems.umamianalytics.ui.theme.UmamiAnalyticsTheme

class MainActivity : ComponentActivity() {

    private val webSitePickerViewModel: WebsitePickerViewModel by viewModels { WebsitePickerViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UmamiAnalyticsTheme {
                ApplicationNavigation(
                    navController = rememberNavController(),
                    websitePickerViewModel = webSitePickerViewModel
                )
            }
        }
    }
}