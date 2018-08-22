package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit

interface AnalyticsContentView : Event {

    fun getViewName(kit : AnalyticsKit): String

}