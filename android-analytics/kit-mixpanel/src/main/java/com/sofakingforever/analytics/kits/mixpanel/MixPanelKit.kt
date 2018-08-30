package com.sofakingforever.analytics.kits.mixpanel

import com.sofakingforever.analytics.AnalyticsKit

class MixPanelKit private constructor() : AnalyticsKit {
    override val name: String = "mixpanel"

    private object Holder {
        val INSTANCE = MixPanelKit()
    }

    companion object {
        val instance: MixPanelKit by lazy { Holder.INSTANCE }
    }


}