package com.sofakingforever.analytics.kits

class FlurryKit private constructor() : AnalyticsKit {

    override val name: String = "flurry"

    private object Holder {
        val INSTANCE = FlurryKit()
    }

    companion object {
        val instance: FlurryKit by lazy { Holder.INSTANCE }
    }


}