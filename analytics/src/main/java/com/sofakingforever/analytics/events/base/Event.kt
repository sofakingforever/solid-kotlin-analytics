package com.sofakingforever.analytics.events.base

import com.sofakingforever.analytics.AnalyticsKit

/**
 * Every event interface should extend this interface. (See CustomEvent or ContentViewEvent)
 *
 * @property excludedKits - if you want to disable a certain kit for an event, you just add the kit to this list.
 * @property includedKits - if you want to send the event to ceratin services, but not other, just add them here.
 */
interface Event {


    val excludedKits: List<AnalyticsKit>
        get() = emptyList()


    val includedKits: List<AnalyticsKit>
        get() = emptyList()


    /**
     * @return false if *kit* is considered "excluded". true if considered "included".
     */
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

}