package com.sofakingforever.library

import android.app.Application
import com.sofakingforever.library.dispatcher.TestableDispatcher
import com.sofakingforever.library.events.InitDispatcherEvent
import com.sofakingforever.library.events.TestCustomEvent
import org.junit.Test


class DispatcherTest {

    private lateinit var raisedException: Exception

    @Test
    fun testDispatcher() {

        val dispatcher = TestableDispatcher()

        dispatcher.initDispatcher(Application())

        dispatcher.track(TestCustomEvent(1))

        dispatcher.track(TestCustomEvent(2))

        dispatcher.track(TestCustomEvent(3))

        dispatcher.track(TestCustomEvent(4))

        dispatcher.track(TestCustomEvent(5))

        val eventList = dispatcher.eventList

        // expect to find 5 events + init event
        assert(eventList.size == 6)
        assert(eventList[0] is InitDispatcherEvent)
        assert((eventList[1] as TestCustomEvent).number == 1)

        // that's enough

    }
}