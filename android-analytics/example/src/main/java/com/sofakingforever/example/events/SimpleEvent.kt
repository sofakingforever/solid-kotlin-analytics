package com.sofakingforever.example.events

import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.AnalyticsKit

class SimpleEvent : CustomEvent {
    override fun getEventName(kit: AnalyticsKit): String = "Simple Event"

}