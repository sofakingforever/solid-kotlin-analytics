package com.sofakingforever.example

import android.app.Application
import android.util.Log
import com.sofaking.moonworshipper.analytics.dispatchers.FirebaseDispatcherImpl
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.AnalyticsSettings
import com.sofakingforever.analytics.dispatchers.AnswersDispatcherImpl

class App : Application() {

    lateinit var analytics: Analytics

    override fun onCreate() {
        super.onCreate()

        // init analytics property. this is in charge of tracking all events
        // you could also try @FlurryDispatcherImpl(), or implement your own
        analytics = Analytics(this, AnswersDispatcherImpl(), FirebaseDispatcherImpl())


        analytics.settings.apply {

            // set analytics enabled / disabled via SharedPrefs, Database, or anything else
            isAnalyticsEnabled = true

            // set an exception handler
            // either way, the analytics util won't crash your app
            exceptionHandler = object : AnalyticsSettings.ExceptionHandler {
                override fun onException(e: Exception) {

                    // this is the exception, log it, send it or ignore it.

                    Log.w("Analytics", "Analytics Exception Raised")

                    e.printStackTrace()
                }

            }

        }

    }
}