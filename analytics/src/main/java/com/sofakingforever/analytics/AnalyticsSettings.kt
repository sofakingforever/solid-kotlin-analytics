package com.sofakingforever.analytics

class AnalyticsSettings {

    @Volatile
    var isAnalyticsEnabled = true

    var exceptionHandler: ExceptionHandler? = null

    interface ExceptionHandler {

        fun onException(e: Exception)

    }


}