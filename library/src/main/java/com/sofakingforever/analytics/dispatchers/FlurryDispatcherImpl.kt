package com.sofaking.moonworshipper.analytics.dispatchers

import android.content.Context
import com.flurry.android.FlurryAgent
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.kits.FlurryKit

class FlurryDispatcherImpl(val apiKey: String) : AnalyticsDispatcher {


    override val kit = FlurryKit.instance

    override fun initDispatcher(context: Context) {
        FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(context, apiKey)

    }

    override fun trackCustomEvent(event: AnalyticsEvent) {


        FlurryAgent.logEvent(event.getEventName(kit), event.createFlurryAnalyticsMap())
    }

    override fun trackContentView(contentView: AnalyticsContentView) {
        FlurryAgent.logEvent("contentView_" + contentView.getViewName())
    }

    override fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent) {
        FlurryAgent.logEvent("inviteEvent_" + inviteEvent.packageName)

    }

    private fun AnalyticsEvent.createFlurryAnalyticsMap(): MutableMap<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()

        this.getParameters().forEach {

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

}