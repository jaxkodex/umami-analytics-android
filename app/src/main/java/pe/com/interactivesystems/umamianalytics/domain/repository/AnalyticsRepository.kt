package pe.com.interactivesystems.umamianalytics.domain.repository

import pe.com.interactivesystems.umamianalytics.domain.model.Website

interface AnalyticsRepository {

    suspend fun websites(): List<Website>
}