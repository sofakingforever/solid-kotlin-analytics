package com.sofakingforever.analytics.events

interface AnalyticsContentView : BaseEvent {

    fun getViewName(): String

}