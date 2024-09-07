package pe.com.interactivesystems.umamianalytics.domain.repository

import pe.com.interactivesystems.umamianalytics.domain.model.Website

interface AnalyticsClient {
    suspend fun websites(): List<Website>
}