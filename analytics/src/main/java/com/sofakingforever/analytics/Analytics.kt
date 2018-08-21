package com.sofakingforever.analytics

import android.content.Context
import android.util.Log
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.events.BaseEvent

class Analytics(context: Context, private vararg val dispatchers: AnalyticsDispatcher) {

    init {
        dispatchers.forEach { dispatcher ->
            dispatcher.initDispatcher(context)
        }
    }

    val settings: AnalyticsSettings = AnalyticsSettings()

    fun track(vararg events: BaseEvent) {

        if (settings.isAnalyticsEnabled.not()) return


        events.forEach {

            dispatchers.forEach { dispatcher ->

                // try / catch for each event / dispatcher combination specifically

                try {
                    when (it) {
                        is AnalyticsEvent -> dispatcher.trackCustomEventBegin(it)
                        is AnalyticsContentView -> dispatcher.trackContentViewBegin(it)
                        is AnalyticsInviteEvent -> dispatcher.trackInviteEventBegin(it)
                        else -> throw UnsupportedAnalyticsEventException(it)
                    }

                } catch (e: Exception) {
                    Log.e("Analytics", "${dispatcher.kit.name} dispatcher couldn't fire \"${it.javaClass.name}\" event", e)
                    settings.exceptionHandler?.onException(e)
                }
            }


        }
    }


}