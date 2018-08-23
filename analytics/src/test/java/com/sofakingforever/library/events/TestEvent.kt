package com.sofakingforever.library.events

import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.CustomEvent

class TestEvent(val number: Int) : CustomEvent {
    override fun getEventName(kit: AnalyticsKit): String = "Test Event $number"

}