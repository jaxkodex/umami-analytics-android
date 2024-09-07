package pe.com.interactivesystems.umamianalytics.domain.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.interactivesystems.umamianalytics.UmamiAnalyticsApplication
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.infra.analytics.ClientException

enum class WebSitePickerUiStatus {
    IDLE, LOADING, SUCCESS, ERROR,
}

data class WebSitePickerUiState(
    val websites: List<Website> = emptyList(),
    val status: WebSitePickerUiStatus = WebSitePickerUiStatus.IDLE,
)

class WebsitePickerViewModel(private val remoteAnalyticsRepository: AnalyticsRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(WebSitePickerUiState())
    val uiState: StateFlow<WebSitePickerUiState> = _uiState.asStateFlow()

    fun load() {
        _uiState.update { it.copy(status = WebSitePickerUiStatus.LOADING) }
        viewModelScope.launch {
            try {
                val websites = remoteAnalyticsRepository.websites()
                _uiState.update {
                    it.copy(
                        websites = websites, status = WebSitePickerUiStatus.SUCCESS
                    )
                }
            } catch (e: ClientException) {
                _uiState.update { it.copy(status = WebSitePickerUiStatus.ERROR) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return WebsitePickerViewModel((application as UmamiAnalyticsApplication).container.remoteAnalyticsRepository) as T
            }
        }
    }
}