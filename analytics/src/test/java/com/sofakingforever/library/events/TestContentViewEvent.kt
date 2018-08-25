package com.sofakingforever.library.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.ContentViewEvent

class TestContentViewEvent(val number: Int) : ContentViewEvent {
    override fun getViewName(kit: AnalyticsKit): String = "Content View #$number"

}