package com.sofakingforever.analytics

import android.content.Context
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.InviteEvent
import com.sofakingforever.analytics.events.SetUserProperty
import com.sofakingforever.analytics.events.base.Event
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

    val dispatcherName : String
    /**
     * Should call the analytics library's initiation methods
     */
    fun initDispatcher(context: Context)


    fun trackContentView(contentView: ContentViewEvent)

    fun trackCustomEvent(event: CustomEvent)

    fun trackInviteEvent(inviteEvent: InviteEvent)

    fun setUserProperty(property: SetUserProperty)

    /**
     * This method is called from the parent @Analytics class for each event.
     * Override this method if you plan on interfacing your own event types.
     */
    fun track(event: Event) {
        if (event.isConsideredIncluded(kit)) {
            // track the event only if it is not configured as excluded
            when (event) {
                // track each type differently
                is CustomEvent -> trackCustomEvent(event)
                is ContentViewEvent -> trackContentView(event)
                is InviteEvent -> trackInviteEvent(event)
                // alert developer if this is a customized event implementation
                else -> throw UnsupportedEventException(event)
            }
        }
    }


}

