package com.sofakingforever.analytics.exceptions

import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.base.Event

class EventNotTrackedException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {

    constructor(dispatcher: AnalyticsDispatcher, event: Event, t: Throwable) : this("${dispatcher.dispatcherName} dispatcher couldn't fire \"${event.javaClass.name}\" event", t)

}