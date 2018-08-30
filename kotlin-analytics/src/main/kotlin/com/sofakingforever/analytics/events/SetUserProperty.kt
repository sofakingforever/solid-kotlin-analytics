package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsKit

interface SetUserProperty : SetUserProperties {

    val key: String
    val value: String

    override fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        val userProperties = super.getUserProperties(kit)
        userProperties[key] = value
        return userProperties
    }
}