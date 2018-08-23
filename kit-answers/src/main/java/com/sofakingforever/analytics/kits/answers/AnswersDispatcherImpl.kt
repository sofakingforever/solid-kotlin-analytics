package com.sofakingforever.analytics.kits.answers

import android.content.Context
import com.crashlytics.android.answers.Answers
import com.sofakingforever.analytics.AnalyticsDispatcher
import com.sofakingforever.analytics.events.SetUserProperty
import io.fabric.sdk.android.Fabric
import io.fabric.sdk.android.Kit

/**
 * @property - fabricKits - if you use this property, you must include a new Answers instance
 */
class AnswersDispatcherImpl(override val init: Boolean, val fabricKits: Kit<*> = Answers()) : AnalyticsDispatcher {

    override var enabled: Boolean = true

    override val kit = AnswersKit.instance

    private val instance: Answers by lazy { Answers.getInstance() }

    override fun initDispatcher(context: Context) {
        // init Fabric with Answers, and any additonal fabric kits supplied
        Fabric.with(context, fabricKits)
    }

    override fun trackCustomEvent(event: com.sofakingforever.analytics.events.CustomEvent) {
        instance.logCustom(event.createAnswersAnalyticsEvent())
    }

    override fun trackContentView(contentView: com.sofakingforever.analytics.events.ContentViewEvent) {
        instance.logContentView(contentView.createAnswersEvent())
    }

    override fun trackInviteEvent(inviteEvent: com.sofakingforever.analytics.events.InviteEvent) {
        instance.logInvite(inviteEvent.createAnswersInviteEvent())
    }

    override fun setUserProperty(property: SetUserProperty) {
        // Answers doesn't support this
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

