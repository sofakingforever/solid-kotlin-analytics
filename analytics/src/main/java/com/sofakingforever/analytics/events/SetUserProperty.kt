package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.events.base.Event

interface SetUserProperty : Event {

    val key : String
    val value : String

}