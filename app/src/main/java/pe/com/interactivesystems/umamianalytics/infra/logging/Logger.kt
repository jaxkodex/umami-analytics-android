package pe.com.interactivesystems.umamianalytics.infra.logging

interface Logger {
    fun e(tag: String, message: String, e: Exception? = null)
}