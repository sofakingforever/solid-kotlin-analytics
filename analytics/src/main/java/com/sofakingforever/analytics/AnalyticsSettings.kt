package com.sofakingforever.analytics

/**
 * Holds some things for the Analytics class
 * @property isAnalyticsEnabled - no events will be sent if this is set to *false*
 * @property exceptionHandler - implementation of @ExceptionHandler
 */
class AnalyticsSettings {

    @Volatile
    var isAnalyticsEnabled = true

    var exceptionHandler: ExceptionHandler? = null

    /**
     * Just an exception callback to log/monitor exceptions,
     * thrown by the *Analytics* class or any of its dispatchers.
     */
    interface ExceptionHandler {

        fun onException(e: Exception)

    }


}