package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.base.Event

interface SetUserProperties : Event {
    fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }
}