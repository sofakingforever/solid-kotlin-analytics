package com.sofakingforever.library

import android.app.Application
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.AnalyticsSettings
import com.sofakingforever.analytics.exceptions.UnsupportedEventException
import com.sofakingforever.library.dispatcher.TestKit
import com.sofakingforever.library.dispatcher.TestableDispatcher
import com.sofakingforever.library.events.TestEvent
import com.sofakingforever.library.events.UnsupportedEvent
import org.junit.Test

class AnalyticsUnitTest {


    private val dispatcher = TestableDispatcher()
    private val analytics = Analytics(Application(), dispatcher).apply {

        // todo - java.lang.RuntimeException: Method e in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details.
        this.log = false

        this.settings.exceptionHandler = object : AnalyticsSettings.ExceptionHandler {
            override fun onException(e: Exception) {
                raisedException = e
            }

        }
    }
    private var raisedException: Exception? = null

    @Test
    fun testAnalytics() {

        // track some events
        trackTestEvents()

        // assert events
        assertEvents()

        // assert no exceptions were raised
        assert(raisedException == null)

        // track an unsupported event
        analytics.track(UnsupportedEvent())


        assert(raisedException != null && raisedException is UnsupportedEventException)

    }


    private fun trackTestEvents() {

        analytics.track(TestEvent(1))

        analytics.setDispatcherEnabled(TestableDispatcher.DispatcherName, false)

        analytics.track(TestEvent(2))

        analytics.setDispatcherEnabled(TestableDispatcher.DispatcherName, true)

        analytics.track(TestEvent(3))

        analytics.setKitEnabled(TestKit.instance, false)

        analytics.track(TestEvent(4))

        analytics.setKitEnabled(TestKit.instance, true)

        analytics.track(TestEvent(5))

    }


    private fun assertEvents() {


        val eventList = dispatcher.eventList

        // expect to find 3 events + init event
        assert(eventList.size == 4)
        assert(eventList[0] is TestableDispatcher.InitEvent)
        assert((eventList[1] as TestEvent).number == 1)
        assert((eventList[2] as TestEvent).number == 3)
        assert((eventList[3] as TestEvent).number == 5)
    }
}