package com.sofakingforever.analytics.kits.flurry

import android.content.Context
import com.flurry.android.FlurryAgent
import com.sofakingforever.analytics.android.AndroidAnalyticsDispatcher
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.SetUserProperties

class FlurryDispatcherImpl(override val context: Context, val apiKey: String) : AndroidAnalyticsDispatcher {

    override val dispatcherName: String = DispatcherName

    override val init: Boolean = true

    override val kit = FlurryKit.instance

    override fun initDispatcher() {
        FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(context, apiKey)

    }

    override fun trackCustomEvent(event: CustomEvent) {


        FlurryAgent.logEvent(event.getEventName(kit), event.createFlurryAnalyticsMap())
    }

    override fun trackContentView(contentView: ContentViewEvent) {
        FlurryAgent.logEvent("contentView_" + contentView.getViewName(kit))
    }

    override fun setUserProperties(properties: SetUserProperties) {
        // Flurry doesn't support this
    }
    private fun CustomEvent.createFlurryAnalyticsMap(): MutableMap<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()

        this.getParameters(kit).forEach {

            if (it.value is Number) {
                map[it.key] = it.value.toString()
            } else if (it.value is String) {
                map[it.key] = it.value as String
            } else if (it.value is Boolean) {
                map[it.key] = (it.value as Boolean).toString()
            } else {
                throw RuntimeException("value type " + it.value.javaClass.toString() + " is illegal")

            }


        }

        return map
    }


    companion object {
        const val DispatcherName = "DefaultFlurryDispatcher"
    }

}