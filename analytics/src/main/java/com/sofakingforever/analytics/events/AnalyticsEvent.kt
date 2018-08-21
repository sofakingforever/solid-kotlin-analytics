package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.kits.AnalyticsKit

interface AnalyticsEvent : BaseEvent {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(): MutableMap<String, Any> {
        return mutableMapOf()
    }

}