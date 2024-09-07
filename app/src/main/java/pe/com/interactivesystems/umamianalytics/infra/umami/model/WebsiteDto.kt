package pe.com.interactivesystems.umamianalytics.infra.umami.model

data class WebsiteDto(
    val id: String,
    val name: String,
    val domain: String,
)

data class WebsiteResponseDto(
    val data: List<WebsiteDto>
)