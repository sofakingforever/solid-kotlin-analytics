package com.sofakingforever.analytics.dispatchers

import android.content.Context
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.crashlytics.android.answers.CustomEvent
import com.crashlytics.android.answers.InviteEvent
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.AnalyticsContentView
import com.sofakingforever.analytics.events.AnalyticsEvent
import com.sofakingforever.analytics.events.AnalyticsInviteEvent
import com.sofakingforever.analytics.events.Event
import com.sofakingforever.analytics.kits.AnswersKit
import io.fabric.sdk.android.Fabric

/**
 *
 */
class AnswersDispatcherImpl(override val init: Boolean) : AnalyticsDispatcher {


    constructor() : this(true)

    override val kit = AnswersKit.instance

    override fun initDispatcher(context: Context) {
        Fabric.with(context, Answers())
    }

    override fun trackCustomEvent(event: AnalyticsEvent) {
        Answers.getInstance().logCustom(event.createAnswersAnalyticsEvent())
    }

    override fun trackContentView(contentView: AnalyticsContentView) {
        Answers.getInstance().logContentView(contentView.createAnswersEvent())
    }

    override fun trackInviteEvent(inviteEvent: AnalyticsInviteEvent) {
        Answers.getInstance().logInvite(inviteEvent.createAnswersInviteEvent())
    }


    private fun AnalyticsEvent.createAnswersAnalyticsEvent(): CustomEvent {
        return CustomEvent(this.getEventName(kit))
                .apply {

                    this@createAnswersAnalyticsEvent.getParameters(kit)
                            .forEach {
                                if (it.value is Number) {
                                    putCustomAttribute(it.key, it.value as Number)
                                } else if (it.value is String) {
                                    putCustomAttribute(it.key, it.value as String)
                                } else if (it.value is Boolean) {
                                    putCustomAttribute(it.key, (it.value as Boolean).toString())
                                } else {
                                    throw RuntimeException("value type " + it.value.javaClass.toString() + " is illegal")
                                }
                            }

                }
    }


    private fun AnalyticsContentView.createAnswersEvent(): ContentViewEvent {
        return ContentViewEvent().putContentName(this.getViewName(kit))
    }

    private fun AnalyticsInviteEvent.createAnswersInviteEvent(): InviteEvent {
        return InviteEvent().putMethod(this.getInviteMethod()).putCustomAttribute("shareVia", this.shareVia)
    }

}

