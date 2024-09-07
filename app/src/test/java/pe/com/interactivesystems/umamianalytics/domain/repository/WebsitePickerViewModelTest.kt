package pe.com.interactivesystems.umamianalytics.domain.repository

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.infra.analytics.ClientException

@ExperimentalCoroutinesApi
class WebsitePickerViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val remoteAnalyticsRepository: AnalyticsRepository = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load should update uiState to SUCCESS when websites are loaded successfully`() = runTest {
        val websites = listOf(
            Website("1", "Example", "example.com"), Website("2", "Another", "another.com")
        )
        whenever(remoteAnalyticsRepository.websites()).doReturn(websites)

        val viewModel = WebsitePickerViewModel(remoteAnalyticsRepository)
        viewModel.load()

        viewModel.uiState.test {
            assertEquals(
                WebSitePickerUiState(websites = websites, status = WebSitePickerUiStatus.SUCCESS),
                awaitItem()
            )
        }
    }

    @Test
    fun `load should update uiState to ERROR when an exception is thrown`() = runTest {
        whenever(remoteAnalyticsRepository.websites()).doAnswer { throw ClientException("Error loading websites") }

        val viewModel = WebsitePickerViewModel(remoteAnalyticsRepository)
        viewModel.load()

        viewModel.uiState.test {
            assertEquals(WebSitePickerUiState(status = WebSitePickerUiStatus.ERROR), awaitItem())
        }
    }
}