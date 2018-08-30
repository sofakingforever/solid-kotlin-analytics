package com.sofakingforever.analytics.kits.firebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.sofakingforever.analytics.android.AndroidAnalyticsDispatcher
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.SetUserProperties

class FirebaseDispatcherImpl(override val init: Boolean, override val context: Context) : AndroidAnalyticsDispatcher {

    override val dispatcherName: String = DispatcherName

    constructor(context: Context) : this(true, context)

    override val kit = FirebaseKit.instance

    var firebaseAnalytics: FirebaseAnalytics? = null


    override fun initDispatcher() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }


    override fun trackCustomEvent(event: CustomEvent) {
        firebaseAnalytics?.logEvent(event.getEventName(kit).firebaseFriendly(), event.getBundle())
    }

    override fun trackContentView(contentView: ContentViewEvent) {
        firebaseAnalytics?.logEvent("contentView_" + contentView.getViewName(kit).firebaseFriendly(), Bundle.EMPTY)
    }

    override fun setUserProperties(properties: SetUserProperties) {
        properties.getUserProperties(kit).forEach {
            firebaseAnalytics?.setUserProperty(it.key, it.value.toString())
        }
    }

    private fun String.firebaseFriendly(): String {

        val firebased = toLowerCase().replace(" ", "_")

        if (firebased.length > 40) {
            throw IllegalStateException("firebase event title shouldn't have more than 40 chars ($firebased)")
        }

        return firebased

    }

    private fun CustomEvent.getBundle(): Bundle {
        val bundle = Bundle()

        getParameters(kit).forEach {
            when {
            // numbers
                it.value is Int -> bundle.putInt(it.key, it.value as Int)
                it.value is Float -> bundle.putFloat(it.key, it.value as Float)
                it.value is Double -> bundle.putDouble(it.key, it.value as Double)
                it.value is Long -> bundle.putLong(it.key, it.value as Long)
            // other stuff
                it.value is String -> bundle.putString(it.key, it.value as String)
                it.value is Boolean -> bundle.putBoolean(it.key, it.value as Boolean)

                else -> throw RuntimeException("value type " + it.value.javaClass.toString() + " is illegal")
            }
        }

        return bundle
    }

    companion object {
        const val DispatcherName = "DefaultFirebaseDispatcher"
    }

}
