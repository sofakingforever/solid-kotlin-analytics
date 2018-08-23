package com.sofakingforever.library

import android.app.Application
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.AnalyticsSettings
import com.sofakingforever.library.dispatcher.TestKit
import com.sofakingforever.library.dispatcher.TestableDispatcher
import com.sofakingforever.library.events.TestEvent
import org.junit.Test


class DispatcherTest {

    private lateinit var raisedException: Exception

    @Test
    fun testDispatcher() {

        val dispatcher = TestableDispatcher()

        dispatcher.initDispatcher(Application())

        dispatcher.track(TestEvent(1))

        dispatcher.track(TestEvent(2))

        dispatcher.track(TestEvent(3))

        dispatcher.track(TestEvent(4))

        dispatcher.track(TestEvent(5))

        val eventList = dispatcher.eventList

        // expect to find 5 events + init event
        assert(eventList.size == 6)
        assert(eventList[0] is TestableDispatcher.InitEvent)
        assert((eventList[1] as TestEvent).number == 1)

        // that's enough

    }
}