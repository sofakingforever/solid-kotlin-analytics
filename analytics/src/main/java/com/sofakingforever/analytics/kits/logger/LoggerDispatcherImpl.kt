package com.sofakingforever.analytics.kits.logger

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.*

/**
 * This is just a logger implementation, Use this if you're in DEBUG mode.
 *
 * Look at AnswersDispatcher or FirebaseDispatcher to learn more about
 * how to implement your own service dispatcher.
 */
class LoggerDispatcherImpl(override val init: Boolean) : AnalyticsDispatcher {


    override val dispatcherName: String = "LoggerDispatcher"

    constructor() : this(true)


    private val tag = dispatcherName

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
        Log.d(tag, "Tracking user property ${property.key} = ${property.value}")
    }
    override fun setUserProperties(properties: SetUserProperties) {
        Log.d(tag, "Tracking user properties ${properties.getUserProperties(kit)}")
    }
}