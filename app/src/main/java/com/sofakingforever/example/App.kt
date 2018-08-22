package com.sofakingforever.example

import android.app.Application
import android.util.Log
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.AnalyticsSettings
import com.sofakingforever.analytics.kits.answers.AnswersDispatcherImpl
import com.sofakingforever.analytics.kits.firebase.FirebaseDispatcherImpl
import com.sofakingforever.example.custom.CustomDispatcher
import com.sofakingforever.analytics.kits.logger.LoggerDispatcherImpl

class App : Application() {

    lateinit var analytics: Analytics

    override fun onCreate() {
        super.onCreate()

        // init analytics property. this is in charge of tracking all events
        analytics = Analytics(this,
                CustomDispatcher(true),
                LoggerDispatcherImpl(),
                AnswersDispatcherImpl(true),
                FirebaseDispatcherImpl(true))


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