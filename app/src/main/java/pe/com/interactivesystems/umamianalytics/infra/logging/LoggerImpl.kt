package pe.com.interactivesystems.umamianalytics.infra.logging

import android.util.Log

class LoggerImpl() : Logger {
    override fun e(tag: String, message: String, e: Exception?) {
        Log.e(tag, message, e)
    }
}