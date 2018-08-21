package com.sofakingforever.analytics

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.events.Event
import com.sofakingforever.analytics.exceptions.UnsupportedAnalyticsEventException

class Analytics(context: Context, private vararg val dispatchers: AnalyticsDispatcher) {


    init {
        dispatchers.forEach { dispatcher ->

            if (dispatcher.init) {
                dispatcher.initDispatcher(context)
            }
        }
    }

    val settings: AnalyticsSettings = AnalyticsSettings()

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