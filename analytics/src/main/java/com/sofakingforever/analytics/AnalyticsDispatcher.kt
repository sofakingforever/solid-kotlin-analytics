package com.sofakingforever.analytics

import android.content.Context
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.events.Event
import com.sofakingforever.analytics.exceptions.UnsupportedEventException

/**
 * AnalyticsDispatcher is an interface that should be implemented for every analytics service.
 * For example: FirebaseDispatcherImpl or AnswersDispatcherImpl
 *
 * @property init - initDispatcher will be called only if this property is set to *true*
 * @property kit - should be represented by a singleton of a class that extends @AnalyticsKit
 */
interface AnalyticsDispatcher {

    val init: Boolean

    val kit: AnalyticsKit

    /**
     * Should call the analytics library's initiation methods
     */
    fun initDispatcher(context: Context)


    fun trackContentView(contentView: AnalyticsContentView)

    fun trackCustomEvent(event: AnalyticsEvent)

    fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent)

    /**
     * This method is called from the parent @Analytics class for each event.
     * Override this method if you plan on interfacing your own event types.
     */
    fun track(event: Event) {
        if (event.isConsideredIncluded(kit)) {
            // track the event only if it is not configured as excluded
            when (event) {
                // track each type differently
                is AnalyticsEvent -> trackCustomEvent(event)
                is AnalyticsContentView -> trackContentView(event)
                is AnalyticsInviteEvent -> trackInviteEvent(event)
                // alert developer if this is a customized event implementation
                else -> throw UnsupportedEventException(event)
            }
        }
    }


}

