package com.sofakingforever.example.events

import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.kits.AnalyticsKit

class ParameterizedEvent(private val isFirstClick : Boolean, private val timeClicked: Long) : AnalyticsEvent {
    override fun getEventName(kit: AnalyticsKit): String = "Event With Parameters"

    override fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        val parameters = super.getParameters(kit)

        parameters["firstClick"] = isFirstClick
        parameters["timeClicked"] = timeClicked

        return parameters
    }

}