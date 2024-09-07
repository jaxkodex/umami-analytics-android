package pe.com.interactivesystems.umamianalytics.infra.umami

import org.junit.Assert.assertEquals
import org.junit.Test
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.infra.umami.model.WebsiteDto

class UmamiAnalyticsClientAdapterKtTest {

    @Test
    fun shouldMapToListOfWebsites_whenInputIsListOfWebsiteDto() {
        val input = listOf(
            WebsiteDto(
                "1", "Example", "example.com"
            )
        )
        val expected = listOf(
            Website(
                "1", "Example", "example.com"
            )
        )
        val result = map(input)
        assertEquals(expected, result)
    }

    @Test
    fun shouldMapToWebsite_whenInputIsWebsiteDto() {
        val input = WebsiteDto(
            "1", "Example", "example.com"
        )
        val expected = Website(
            "1", "Example", "example.com"
        )
        val result = map(input)
        assertEquals(expected, result)
    }
}