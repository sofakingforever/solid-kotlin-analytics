package com.sofakingforever.example.events

import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.AnalyticsKit

class MainContentView : ContentViewEvent {
    override fun getViewName(kit : AnalyticsKit): String = "Main Screen"


}