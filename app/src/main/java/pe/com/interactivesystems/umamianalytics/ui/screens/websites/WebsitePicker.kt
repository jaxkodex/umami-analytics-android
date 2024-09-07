package pe.com.interactivesystems.umamianalytics.ui.screens.websites

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.domain.repository.WebsitePickerViewModel
import pe.com.interactivesystems.umamianalytics.navigation.Screen
import pe.com.interactivesystems.umamianalytics.ui.theme.UmamiAnalyticsTheme

private const val TAG = "WebsitePicker"

@Composable
fun WebsitePicker(websitePickerViewModel: WebsitePickerViewModel, navController: NavController) {
    val uiState = websitePickerViewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        Log.d(TAG, "LaunchedEffect")
        websitePickerViewModel.load()
    }

    WebsitePicker(websites = uiState.value.websites, navController = navController)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WebsitePickerLayout(content: @Composable (innerPadding: PaddingValues) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Pick your website") }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back navigation"
                )
            }
        })
    }) { innerPadding ->
        content(innerPadding)
    }
}

@Composable
fun WebsitePicker(
    websites: List<Website>,
    navController: NavController
) {
    WebsitePickerLayout { innerPadding ->
        WebsitePicker(
            modifier = Modifier.padding(innerPadding),
            websites = websites,
            onWebsiteSelected = { website ->
                navController.navigate(Screen.WebsiteHome.build(website.id))
            })
    }
}

@Composable
fun WebsitePicker(
    modifier: Modifier = Modifier,
    websites: List<Website>,
    onWebsiteSelected: (website: Website) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(websites.size, key = { websites[it].id }) {
            WebsitePickerItem(website = websites[it], onClick = onWebsiteSelected)
        }
    }
}

@Composable
fun WebsitePickerItem(website: Website, onClick: (website: Website) -> Unit = {}) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(website) }) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = website.name)
            Text(text = website.domain, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WebsitePickerPreview() {
    UmamiAnalyticsTheme {
        WebsitePicker(
            websites = listOf(
                Website(id = "1", name = "Google", domain = "google.com"),
                Website(id = "2", name = "Facebook", domain = "facebook.com"),
                Website(id = "3", name = "Twitter", domain = "twitter.com"),
            )
        )
    }
}