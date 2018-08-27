package com.sofakingforever.library.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.CustomEvent

class TestCustomEvent(val number: Int) : CustomEvent {
    override fun getEventName(kit: AnalyticsKit): String = "Custom Event #$number"

    override fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        val parameters = super.getParameters(kit)
        parameters["int"] = -2
        parameters["long"] = +2L
        parameters["boolean"] = true
        parameters["string"] = "fun #$number"
        return parameters
    }
}