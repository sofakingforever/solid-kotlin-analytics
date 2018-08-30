package com.sofakingforever.library.events

import com.sofakingforever.analytics.events.base.Event

/**
 * This unsupported event is not handled in the Dispatcher.
 * You must override the dispatcher's track function to handle it
 */
class UnsupportedEvent : Event