package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit

interface AnalyticsEvent : Event {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }

}