package pe.com.interactivesystems.umamianalytics

import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsClient
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsRepository
import pe.com.interactivesystems.umamianalytics.infra.analytics.RemoteAnalyticsRepository
import pe.com.interactivesystems.umamianalytics.infra.logging.LoggerImpl
import pe.com.interactivesystems.umamianalytics.infra.umami.UmamiAnalyticsClient
import pe.com.interactivesystems.umamianalytics.infra.umami.UmamiAnalyticsClientAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationContainer {

    private val umamiApi = Retrofit.Builder()
        .baseUrl("https://api.umami.is")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UmamiAnalyticsClient::class.java)

    private val analyticsClient: AnalyticsClient = UmamiAnalyticsClientAdapter(umamiApi)

    private val logger = LoggerImpl()

    val remoteAnalyticsRepository: AnalyticsRepository =
        RemoteAnalyticsRepository(analyticsClient, logger)
}