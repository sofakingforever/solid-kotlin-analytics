package com.sofakingforever.analytics.kits.logger

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.InviteEvent
import com.sofakingforever.analytics.events.SetUserProperty

/**
 * This is just a logger implementation, Use this if you're in DEBUG mode.
 *
 * Look at AnswersDispatcher or FirebaseDispatcher to learn more about
 * how to implement your own service dispatcher.
 */
class LoggerDispatcherImpl(override val init: Boolean) : AnalyticsDispatcher {

    constructor() : this(true)

    override var enabled: Boolean = true

    private val tag = "LoggerDispatcher"

    override val kit: AnalyticsKit = LoggerKit.instance

    override fun initDispatcher(context: Context) {
        Log.d(tag, "Init Logger Analytics Dispatcher")
    }

    override fun trackContentView(contentView: ContentViewEvent) {
        Log.d(tag, "Tracking contentView ${contentView.getViewName(kit)}")
    }

    override fun trackCustomEvent(event: CustomEvent) {
        Log.d(tag, "Tracking event ${event.getEventName(kit)}")
    }

    override fun trackInviteEvent(inviteEvent: InviteEvent) {
        Log.d(tag, "Tracking inviteEvent ${inviteEvent.packageName}")
    }
    override fun setUserProperty(property: SetUserProperty) {
        // used only for certain analytics
    }
}