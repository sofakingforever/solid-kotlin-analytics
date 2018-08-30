package com.sofakingforever.example.custom

import com.sofakingforever.analytics.AnalyticsKit

class CustomKit private constructor() : AnalyticsKit {
    override val name: String = "Custom Dispatcher"


    private object Holder {
        val INSTANCE = CustomKit()
    }

    companion object {
        val instance: CustomKit by lazy { Holder.INSTANCE }
    }


}