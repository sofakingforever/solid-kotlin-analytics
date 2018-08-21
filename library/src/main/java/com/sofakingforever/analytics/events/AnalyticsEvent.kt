package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsDispatcher

interface AnalyticsEvent : BaseEvent {

    fun getEventName(kit: AnalyticsDispatcher.Kit): String

    fun getParameters(): MutableMap<String, Any> {
        return mutableMapOf()
    }

}