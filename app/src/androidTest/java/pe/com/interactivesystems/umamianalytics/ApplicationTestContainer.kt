package pe.com.interactivesystems.umamianalytics

import org.mockito.Mockito
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsClient
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsRepository
import pe.com.interactivesystems.umamianalytics.infra.analytics.RemoteAnalyticsRepository
import pe.com.interactivesystems.umamianalytics.infra.logging.LoggerImpl
import pe.com.interactivesystems.umamianalytics.infra.umami.UmamiAnalyticsClient
import pe.com.interactivesystems.umamianalytics.infra.umami.UmamiAnalyticsClientAdapter

class ApplicationTestContainer(umamiApi: UmamiAnalyticsClient) {

    private val analyticsClient: AnalyticsClient = UmamiAnalyticsClientAdapter(umamiApi)

    private val logger = LoggerImpl()

    val remoteAnalyticsRepository: AnalyticsRepository =
        RemoteAnalyticsRepository(analyticsClient, logger)
}