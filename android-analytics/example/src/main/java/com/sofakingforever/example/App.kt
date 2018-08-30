package com.sofakingforever.example

import android.app.Application
import android.util.Log
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.AnalyticsSettings
import com.sofakingforever.analytics.android.AndroidAnalyticsSettings
import com.sofakingforever.analytics.kits.answers.AnswersDispatcherImpl
import com.sofakingforever.analytics.kits.firebase.FirebaseDispatcherImpl
import com.sofakingforever.analytics.android.kits.logger.LoggerDispatcherImpl
import com.sofakingforever.analytics.kits.mixpanel.MixPanelDispatcherImpl
import com.sofakingforever.example.custom.CustomDispatcher

class App : Application() {

    lateinit var analytics: Analytics

    override fun onCreate() {
        super.onCreate()


        // set an analytics enabled / disabled via SharedPrefs, Database, or anything else
        val settings = AndroidAnalyticsSettings(this).also {
            it.isAnalyticsEnabled = true

        }

        // init analytics property. this is in charge of tracking all events
        analytics = Analytics(settings,
                CustomDispatcher(init = true),
                LoggerDispatcherImpl(init = true, context = this),
                FirebaseDispatcherImpl(init = true, context = this),
                MixPanelDispatcherImpl(init = true, projectToken = "TOKEN", context = this),
                AnswersDispatcherImpl(init = true, context = this)


//              if you're using crashlytics, or any other fabric kit in addition to Answers
//              AnswersDispatcherImpl(init = true, Answers(), Crashlytics())
        ).also {

            // set an exception handler
            // either way, the analytics util won't crash your app
            it.exceptionHandler = object : Analytics.ExceptionHandler {
                override fun onException(e: Exception) {

                    // this is the exception, log it, send it or ignore it.
                    Log.w("Analytics", "Analytics Exception Raised")

                    e.printStackTrace()
                }

            }
        }


    }
}