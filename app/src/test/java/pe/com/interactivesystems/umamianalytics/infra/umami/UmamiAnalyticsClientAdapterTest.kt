package pe.com.interactivesystems.umamianalytics.infra.umami

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.infra.umami.model.WebsiteDto
import pe.com.interactivesystems.umamianalytics.infra.umami.model.WebsiteResponseDto

@RunWith(MockitoJUnitRunner::class)
class UmamiAnalyticsClientAdapterTest {
    @Mock
    private lateinit var api: UmamiAnalyticsClient

    @InjectMocks
    private lateinit var sut: UmamiAnalyticsClientAdapter

    @Test
    fun setToken() {
        val input = "token"
        sut.token = input
        assertEquals(input, sut.token)
    }

    @Test
    fun websites() = runTest {
        val token = "token"
        sut.token = token
        doReturn(WebsiteResponseDto(listOf(WebsiteDto("1", "Example", "example.com")))).`when`(api)
            .websites(token)

        val result = sut.websites()
        val expected = listOf(Website("1", "Example", "example.com"))

        assertEquals(expected, result)
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowException_whenTokenIsNotSet(): Unit = runTest {
        sut.websites()
    }
}