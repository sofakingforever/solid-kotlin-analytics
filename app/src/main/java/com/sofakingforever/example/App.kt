package com.sofakingforever.example

import android.app.Application
import com.sofaking.moonworshipper.analytics.dispatchers.FirebaseDispatcherImpl
import com.sofakingforever.analytics.Analytics
import com.sofakingforever.analytics.dispatchers.AnswersDispatcherImpl

class App : Application() {

    lateinit var analytics: Analytics

    override fun onCreate() {
        super.onCreate()

        analytics = Analytics(this, AnswersDispatcherImpl(), FirebaseDispatcherImpl())

    }
}