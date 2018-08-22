package com.sofakingforever.analytics

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.events.base.Event

/**
 * The *Analytics* class is in charge of tracking any *Event* implementation.
 *
 * @property context any context from the app
 * @property dispatchers list of *AnalyticsDispatchers* to trigger for every event
 *
 * @constructor create an instance of the *Analytics* class
 */
class Analytics(context: Context, private vararg val dispatchers: AnalyticsDispatcher) {

    val settings: AnalyticsSettings = AnalyticsSettings()

    init {
        dispatchers.forEach { dispatcher ->
            if (dispatcher.init) {
                dispatcher.initDispatcher(context)
            }
        }
    }

    /**
     * Call this to track one or more *Events*
     */
    fun track(vararg events: Event) {

        if (settings.isAnalyticsEnabled.not()) return

        events.forEach {

            dispatchers.forEach { dispatcher ->

                try {
                    dispatcher.track(it)
                } catch (e: Exception) {
                    Log.e("Analytics", "${dispatcher.kit.name} dispatcher couldn't fire \"${it.javaClass.name}\" event", e)
                    settings.exceptionHandler?.onException(e)
                }
            }


        }
    }


}