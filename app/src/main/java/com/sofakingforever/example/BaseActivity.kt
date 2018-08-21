package com.sofakingforever.example

import android.support.v4.app.FragmentActivity
import com.sofakingforever.analytics.Analytics

abstract class BaseActivity : FragmentActivity() {

    val app: App
        get() {
            return application as App
        }

    val analytics: Analytics
        get() {
            return app.analytics
        }


}