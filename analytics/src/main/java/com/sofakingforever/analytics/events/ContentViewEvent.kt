package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.base.Event

/**
 * Implement this interface in your class, if it is a ContentView event.
 * It will only have a name. nothing else.
 */
interface ContentViewEvent : Event {

    fun getViewName(kit : AnalyticsKit): String

}