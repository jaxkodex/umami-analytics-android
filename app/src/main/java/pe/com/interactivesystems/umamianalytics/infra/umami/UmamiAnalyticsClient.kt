package pe.com.interactivesystems.umamianalytics.infra.umami

import pe.com.interactivesystems.umamianalytics.infra.umami.model.WebsiteResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface UmamiAnalyticsClient {

    @GET("/v1/websites")
    suspend fun websites(@Header("x-umami-api-key") token: String): WebsiteResponseDto
}