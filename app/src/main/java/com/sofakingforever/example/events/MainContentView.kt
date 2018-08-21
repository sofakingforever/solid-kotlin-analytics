package com.sofakingforever.example.events

import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.kits.AnalyticsKit

class MainContentView : AnalyticsContentView {
    override fun getViewName(kit : AnalyticsKit): String = "Main Screen"


}