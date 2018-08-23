package com.sofakingforever.library.dispatcher

import android.content.Context
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.InviteEvent
import com.sofakingforever.analytics.events.SetUserProperty
import com.sofakingforever.analytics.events.base.Event
import com.sofakingforever.library.events.InitDispatcherEvent

class TestableDispatcher : AnalyticsDispatcher {

    override val init: Boolean = true

    override val kit: AnalyticsKit = TestKit.instance

    override val dispatcherName: String = DispatcherName

    val eventList: MutableList<Event> = mutableListOf()

    override fun initDispatcher(context: Context) {
        track(InitDispatcherEvent())
    }

    override fun trackContentView(contentView: ContentViewEvent) {
        eventList.add(contentView)
    }

    override fun trackCustomEvent(event: CustomEvent) {
        eventList.add(event)
    }

    override fun trackInviteEvent(inviteEvent: InviteEvent) {
        eventList.add(inviteEvent)
    }

    override fun setUserProperty(property: SetUserProperty) {
        eventList.add(property)
    }

    override fun track(event: Event) {
        if (event is InitDispatcherEvent) {
            eventList.add(event)
        } else {
            super.track(event)
        }
    }

    companion object {
        const val DispatcherName = "TestDispatcher"
    }

}