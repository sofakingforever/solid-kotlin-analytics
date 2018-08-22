package com.sofakingforever.example.custom

import android.content.Context
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.InviteEvent
import com.sofakingforever.analytics.AnalyticsKit

/**
 * Look at any of the library's Dispatcher Implementations to learn how to implement one yourself
 */
class CustomDispatcher(override val init: Boolean) : AnalyticsDispatcher {

    constructor() : this(true)

    override val kit: AnalyticsKit = CustomKit.instance

    override fun initDispatcher(context: Context) {
        // call custom analytics initiation function
    }

    override fun trackContentView(contentView: ContentViewEvent) {
        // track content view
    }

    override fun trackCustomEvent(event: CustomEvent) {
        // track event
    }

    override fun trackInviteEvent(inviteEvent: InviteEvent) {
        // track invite event
    }

}