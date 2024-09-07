package pe.com.interactivesystems.umamianalytics.infra.umami

import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsClient
import pe.com.interactivesystems.umamianalytics.infra.umami.model.WebsiteDto

class UmamiAnalyticsClientAdapter(private val api: UmamiAnalyticsClient) : AnalyticsClient {
    lateinit var token: String

    override suspend fun websites(): List<Website> {
        checkTokenIsSet()
        return map(api.websites(token).data)
    }

    private fun checkTokenIsSet() {
        if (!::token.isInitialized) {
            throw IllegalStateException("Token not set")
        }
    }
}

fun map(input: List<WebsiteDto>): List<Website> =
    input.map {
        map(it)
    }

fun map(input: WebsiteDto): Website =
    Website(
        id = input.id, name = input.name, domain = input.domain
    )