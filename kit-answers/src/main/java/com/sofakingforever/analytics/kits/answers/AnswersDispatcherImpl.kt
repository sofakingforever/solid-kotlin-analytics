package com.sofakingforever.analytics.kits.answers

import android.content.Context
import com.crashlytics.android.answers.Answers
import com.sofakingforever.analytics.AnalyticsDispatcher
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

    override fun trackCustomEvent(event: com.sofakingforever.analytics.events.CustomEvent) {
        Answers.getInstance().logCustom(event.createAnswersAnalyticsEvent())
    }

    override fun trackContentView(contentView: com.sofakingforever.analytics.events.ContentViewEvent) {
        Answers.getInstance().logContentView(contentView.createAnswersEvent())
    }

    override fun trackInviteEvent(inviteEvent: com.sofakingforever.analytics.events.InviteEvent) {
        Answers.getInstance().logInvite(inviteEvent.createAnswersInviteEvent())
    }


    private fun com.sofakingforever.analytics.events.CustomEvent.createAnswersAnalyticsEvent(): com.crashlytics.android.answers.CustomEvent {
        return com.crashlytics.android.answers.CustomEvent(this.getEventName(kit))
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


    private fun com.sofakingforever.analytics.events.ContentViewEvent.createAnswersEvent(): com.crashlytics.android.answers.ContentViewEvent {
        return com.crashlytics.android.answers.ContentViewEvent().putContentName(this.getViewName(kit))
    }

    private fun com.sofakingforever.analytics.events.InviteEvent.createAnswersInviteEvent(): com.crashlytics.android.answers.InviteEvent {
        return com.crashlytics.android.answers.InviteEvent().putMethod(this.getInviteMethod()).putCustomAttribute("shareVia", this.shareVia)
    }

}

