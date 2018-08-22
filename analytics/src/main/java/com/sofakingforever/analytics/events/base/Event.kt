package com.sofakingforever.analytics.events.base

import com.sofakingforever.analytics.AnalyticsKit

/**
 * Every event interface (See CustomEvent or ContentViewEvent) should extend this interface.
 */
interface Event {
    fun isConsideredIncluded(kit: AnalyticsKit): Boolean {

        if (excludedKits.contains(kit)) {
            // not included if declared excluded
            return false
        }


        if (includedKits.isNotEmpty() && includedKits.contains(kit).not()) {
            // not included if includedKits has anything, but doesn't have this kit
            return false
        }

        return true
    }

    val excludedKits: List<AnalyticsKit>
        get() = emptyList()


    val includedKits: List<AnalyticsKit>
        get() = emptyList()

}