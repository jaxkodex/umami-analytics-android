package pe.com.interactivesystems.umamianalytics.infra.analytics

import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsClient
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsRepository
import pe.com.interactivesystems.umamianalytics.infra.logging.Logger

private const val TAG = "RemoteAnalyticsRepository"

class RemoteAnalyticsRepository(private val api: AnalyticsClient, private val logger: Logger) :
    AnalyticsRepository {
    @Throws(ClientException::class)
    override suspend fun websites(): List<Website> {
        try {
            return api.websites()
        } catch (e: Exception) {
            logger.e(TAG, "Error loading websites: ${e.message}", e)
            throw ClientException(e.message ?: "Unknown error")
        }
    }
}

class ClientException(message: String) : Exception(message)
