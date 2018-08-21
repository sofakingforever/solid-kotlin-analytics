package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.kits.AnalyticsKit

interface AnalyticsContentView : BaseEvent {

    fun getViewName(kit : AnalyticsKit): String

}