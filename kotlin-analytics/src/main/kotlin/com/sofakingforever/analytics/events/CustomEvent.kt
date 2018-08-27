package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.base.Event

/**
 * Implement this interface in your class, if it is a "custom" event.
 *
 * It will carry a name, and a parameter map.
 * Both might vary, according to the kit parameter supplied.
 */
interface CustomEvent : Event {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }

}