package com.sofakingforever.library

import android.content.Context
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.AnalyticsSettings
import com.sofakingforever.analytics.exceptions.EventNotTrackedException
import com.sofakingforever.analytics.exceptions.UnsupportedEventException
import com.sofakingforever.library.dispatcher.TestKit
import com.sofakingforever.library.dispatcher.TestableDispatcher
import com.sofakingforever.library.events.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class AnalyticsUnitTest {

    private val contextMock = mock(Context::class.java)

    private lateinit var analytics: Analytics

    private val dispatcher = TestableDispatcher()
    private var raisedException: Exception? = null

    init {
        Mockito.`when`(contextMock.applicationContext).thenReturn(contextMock)
    }

    @Test
    fun testAnalytics() {

        analytics = Analytics(AnalyticsSettings(contextMock), dispatcher).apply {

            this.exceptionHandler = object : Analytics.ExceptionHandler {
                override fun onException(e: Exception) {
                    raisedException = e
                }

            }
        }

        // track some events
        trackTestEvents()

        // assert events
        assertEvents()

        // assert no exceptions were raised
        assert(raisedException == null)

        // track an unsupported event
        analytics.track(UnsupportedEvent())

        // assert UnsupportedEventException was raised
        assert(raisedException != null)
        assert(raisedException is EventNotTrackedException)
        assert((raisedException as EventNotTrackedException).cause is UnsupportedEventException)

    }


    private fun trackTestEvents() {

        analytics.track(TestCustomEvent(1))

        analytics.setDispatcherEnabled(TestableDispatcher.DispatcherName, false)

        analytics.track(TestCustomEvent(2))

        analytics.setDispatcherEnabled(TestableDispatcher.DispatcherName, true)

        analytics.track(TestCustomEvent(3))

        analytics.setKitEnabled(TestKit.instance, false)

        analytics.track(TestCustomEvent(4))

        analytics.setKitEnabled(TestKit.instance, true)

        analytics.track(TestCustomEvent(5))

        analytics.track(TestContentViewEvent(6))

        analytics.track(TestUserProperties(7))

    }


    private fun assertEvents() {


        val eventList = dispatcher.eventList

        // expect to find 3 custom events , 1 contentview and 1 init event
        assert(eventList.size == 6)
        assert(eventList[0] is InitDispatcherEvent)
        assert((eventList[1] as TestCustomEvent).number == 1)
        assert((eventList[2] as TestCustomEvent).number == 3)
        assert((eventList[3] as TestCustomEvent).number == 5)
        assert((eventList[4] as TestContentViewEvent).number == 6)
        assert((eventList[5] as TestUserProperties).number == 7)
        val mapValues = (eventList[5] as TestUserProperties).getUserProperties(TestKit.instance).toString()


    }
}