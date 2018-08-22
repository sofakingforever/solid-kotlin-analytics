package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.base.Event

interface ContentViewEvent : Event {

    fun getViewName(kit : AnalyticsKit): String

}