package com.sofakingforever.analytics.events

import com.sofakingforever.analytics.AnalyticsDispatcher

interface BaseEvent {
    fun isConsideredIncluded(kit: AnalyticsDispatcher.Kit): Boolean {

        if (excludedKits.contains(kit)) {
            // not included if declared excluded
            return false
        }


        if (includedKits.isNotEmpty() && includedKits.contains(kit).not()) {
            // not included if includedKits has anything, but doesn have this kit
            return false
        }

        return true
    }

    val excludedKits: List<AnalyticsDispatcher.Kit>
        get() = emptyList()


    val includedKits: List<AnalyticsDispatcher.Kit>
        get() = emptyList()

}