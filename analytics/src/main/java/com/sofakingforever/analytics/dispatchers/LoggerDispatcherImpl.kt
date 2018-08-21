package com.sofakingforever.example.custom

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.kits.AnalyticsKit

/**
 * Look at AnswersDispatcher, FirebaseDispatcher or FlurryDispatcher to know more
 */
class LoggerDispatcherImpl(override val init: Boolean) : AnalyticsDispatcher {

    constructor() : this(true)

    val tag = "LoggerDispatcher"

    override val kit: AnalyticsKit = LoggerKit.instance

    override fun initDispatcher(context: Context) {
        Log.d(tag, "Init Logger Analytics Dispatcher")
    }

    override fun trackContentView(contentView: AnalyticsContentView) {
        Log.d(tag, "Tracking contentView ${contentView.getViewName(kit)}")
    }

    override fun trackCustomEvent(event: AnalyticsEvent) {
        Log.d(tag, "Tracking event ${event.getEventName(kit)}")
    }

    override fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent) {
        Log.d(tag, "Tracking inviteEvent ${inviteEvent.packageName}")
    }

}