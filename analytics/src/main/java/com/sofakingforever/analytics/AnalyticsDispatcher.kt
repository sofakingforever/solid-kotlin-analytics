package com.sofakingforever.analytics

import android.content.Context
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.events.Event
import com.sofakingforever.analytics.exceptions.UnsupportedAnalyticsEventException

interface AnalyticsDispatcher {

    val init: Boolean

    val kit: AnalyticsKit

    fun initDispatcher(context: Context)

    fun trackContentView(contentView: AnalyticsContentView)

    fun trackCustomEvent(event: AnalyticsEvent)

    fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent)

    fun track(event : Event){
        if (event.isConsideredIncluded(kit)){
            when (event){
                is AnalyticsEvent -> trackCustomEvent(event)
                is AnalyticsContentView -> trackContentView(event)
                is AnalyticsInviteEvent -> trackInviteEvent(event)
                else -> throw UnsupportedAnalyticsEventException(event)
            }
        }
    }



}

