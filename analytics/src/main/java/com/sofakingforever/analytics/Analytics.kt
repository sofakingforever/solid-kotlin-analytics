package com.sofakingforever.analytics

import android.content.Context
import com.sofakingforever.analytics.events.base.Event
import com.sofakingforever.analytics.exceptions.EventNotTrackedException
import com.sofakingforever.analytics.version.VersionChecker

/**
 * The *Analytics* class is in charge of tracking any *Event* implementation.
 *
 * @param context any context from the app
 * @property dispatchers list of *AnalyticsDispatchers* to trigger for every event
 *
 * @constructor create an instance of the *Analytics* class
 */
class Analytics(context: Context, val settings: AnalyticsSettings, private vararg val dispatchers: AnalyticsDispatcher) {


    var exceptionHandler: ExceptionHandler? = null


    init {

        // check for new library version if enabled
        if (settings.checkForUpdates) VersionChecker.onCheckVersion()

        // init all dispatchers
        dispatchers.forEach { dispatcher ->
            if (dispatcher.init) {
                dispatcher.initDispatcher(context.applicationContext)
            }
        }

    }


    /**
     * Call this to track one or more *Events*
     */
    fun track(vararg events: Event) {

        if (settings.isAnalyticsEnabled.not()) return


        events.forEach { event ->

            dispatchers.forEach { dispatcher ->

                if (settings.enabledKits.isDisabled(dispatcher.kit)) return

                if (settings.enabledDispatchers.isDisabled(dispatcher.dispatcherName)) return

                try {
                    dispatcher.track(event)
                } catch (e: Exception) {
                    exceptionHandler?.onException(EventNotTrackedException(dispatcher, event, e))
                }
            }


        }
    }

    /**
     * Set Kit as enabled or disabled for future event dispatches
     */
    fun setKitEnabled(kit: AnalyticsKit, enabled: Boolean) {
        settings.enabledKits[kit] = enabled
    }

    /**
     * Set Dispatcher as enabled or disabled for future event dispatches
     */
    fun setDispatcherEnabled(dispatcherName: String, enabled: Boolean) {
        settings.enabledDispatchers[dispatcherName] = enabled
    }

    /**
     * Just an exception callback to log/monitor exceptions,
     * thrown by the *Analytics* class or any of its dispatchers.
     */
    interface ExceptionHandler {

        fun onException(e: Exception)

    }

}