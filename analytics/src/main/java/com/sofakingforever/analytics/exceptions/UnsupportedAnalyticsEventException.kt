package com.sofakingforever.analytics.exceptions

import com.sofakingforever.analytics.events.Event

class UnsupportedAnalyticsEventException(event: Event) : UnsupportedOperationException() {
    override val message: String = "couldn't fire \"${event.javaClass.name}\" event"
}
