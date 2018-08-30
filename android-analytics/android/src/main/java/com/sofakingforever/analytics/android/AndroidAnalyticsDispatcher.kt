package com.sofakingforever.analytics.android

import android.content.Context
import com.sofakingforever.analytics.AnalyticsDispatcher

interface AndroidAnalyticsDispatcher : AnalyticsDispatcher {

    val context: Context

}