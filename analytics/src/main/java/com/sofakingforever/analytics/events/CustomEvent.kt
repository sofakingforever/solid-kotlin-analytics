package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.base.Event

interface CustomEvent : Event {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }

}