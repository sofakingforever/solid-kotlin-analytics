package com.sofakingforever.example.custom

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.kits.AnalyticsKit
import com.sofakingforever.example.custom.CustomKit

/**
 * Look at any of the library's Dispatcher Implementations to learn how to implement one yourself
 */
class CustomDispatcher(override val init: Boolean) : AnalyticsDispatcher {

    constructor() : this(true)

    override val kit: AnalyticsKit = CustomKit.instance

    override fun initDispatcher(context: Context) {
        // call custom analytics initiation function
    }

    override fun trackContentView(contentView: AnalyticsContentView) {
        // track content view
    }

    override fun trackCustomEvent(event: AnalyticsEvent) {
        // track event
    }

    override fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent) {
        // track invite event
    }

}