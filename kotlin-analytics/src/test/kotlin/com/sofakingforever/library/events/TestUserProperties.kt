package com.sofakingforever.library.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.SetUserProperties

class TestUserProperties(val number: Int) : SetUserProperties {
    override fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        val parameters = super.getUserProperties(kit)
        parameters["int"] = -2
        parameters["long"] = +2L
        parameters["boolean"] = true
        parameters["string"] = "fun #$number"
        return parameters
    }
}