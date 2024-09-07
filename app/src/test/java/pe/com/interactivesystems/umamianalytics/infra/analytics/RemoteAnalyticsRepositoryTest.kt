package pe.com.interactivesystems.umamianalytics.infra.analytics

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import pe.com.interactivesystems.umamianalytics.domain.model.Website
import pe.com.interactivesystems.umamianalytics.domain.repository.AnalyticsClient
import pe.com.interactivesystems.umamianalytics.infra.logging.Logger

@RunWith(MockitoJUnitRunner::class)
class RemoteAnalyticsRepositoryTest {
    @Mock
    private lateinit var logger: Logger;

    @Mock
    private lateinit var analyticsClient: AnalyticsClient;

    @InjectMocks
    private lateinit var sut: RemoteAnalyticsRepository;

    @Test
    fun websites() = runTest {
        val expected = listOf(Website("1", "Example", "example.com"))
        doReturn(expected).`when`(analyticsClient).websites()

        val result = sut.websites()

        Assert.assertEquals(expected, result)
    }
}