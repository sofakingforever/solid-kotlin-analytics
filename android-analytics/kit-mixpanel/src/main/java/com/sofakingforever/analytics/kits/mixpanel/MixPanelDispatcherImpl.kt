package com.sofakingforever.analytics.kits.mixpanel

import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.AnalyticsKit
import com.sofakingforever.analytics.events.ContentViewEvent
import com.sofakingforever.analytics.events.CustomEvent
import com.sofakingforever.analytics.events.InviteEvent
import com.sofakingforever.analytics.events.SetUserProperties


class MixPanelDispatcherImpl(override val init: Boolean = false, private val projectToken: String? = null) : AnalyticsDispatcher {


    override val dispatcherName: String = DispatcherName


    override val kit: AnalyticsKit = MixPanelKit.instance

    private lateinit var mixpanel: MixpanelAPI

    override fun initDispatcher(context: Context) {
        mixpanel = MixpanelAPI.getInstance(context, projectToken)
    }

    override fun trackContentView(contentView: ContentViewEvent) {
        mixpanel.track("contentView_" + contentView.getViewName(kit))
    }

    override fun trackCustomEvent(event: CustomEvent) {
        mixpanel.trackMap(event.getEventName(kit), event.getParameters(kit))
    }

    override fun trackInviteEvent(inviteEvent: InviteEvent) {
        // not implementing for mixpanel
    }

    override fun setUserProperties(properties: SetUserProperties) {
        mixpanel.people.setMap(properties.getUserProperties(kit))
    }

    companion object {
        const val DispatcherName = "DefaultMixPanelDispatcher"
    }
}