package com.sofakingforever.analytics

import com.sofakingforever.analytics.events.BaseEvent

class UnsupportedAnalyticsEventException(event: BaseEvent) : UnsupportedOperationException() {
    override val message: String = "couldn't fire \"${event.javaClass.name}\" event"
}
