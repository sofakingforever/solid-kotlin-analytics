package com.sofakingforever.analytics

import android.content.Context
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent

interface AnalyticsDispatcher {

    val kit: Kit

    fun initDispatcher(context: Context)

    fun trackContentViewBegin(contentView: AnalyticsContentView) {

        if (contentView.isConsideredIncluded(kit)) {
            trackContentView(contentView)
        }


    }

    fun trackContentView(contentView: AnalyticsContentView)

    fun trackCustomEventBegin(event: AnalyticsEvent) {
        if (event.isConsideredIncluded(kit)) {
            trackCustomEvent(event)
        }

    }

    fun trackCustomEvent(event: AnalyticsEvent)

    fun trackInviteEventBegin(event: AnalyticsInviteEvent) {
        if (event.isConsideredIncluded(kit)) {
            trackInviteEvent(event)
        }
    }

    fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent)

    enum class Kit {

        Answers, Firebase, Flurry, Other

    }

}

