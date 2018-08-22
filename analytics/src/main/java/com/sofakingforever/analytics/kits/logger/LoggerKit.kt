package com.sofakingforever.example.custom

import com.sofakingforever.analytics.AnalyticsKit

class LoggerKit private constructor() : AnalyticsKit {
    override val name: String = "Logcat Dispatcher"


    private object Holder {
        val INSTANCE = LoggerKit()
    }

    companion object {
        val instance: LoggerKit by lazy { Holder.INSTANCE }
    }


}