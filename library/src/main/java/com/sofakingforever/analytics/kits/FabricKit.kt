package com.sofakingforever.analytics.kits

class FabricKit private constructor() : AnalyticsKit {

    private object Holder {
        val INSTANCE = FabricKit()
    }

    companion object {
        val instance: FabricKit by lazy { Holder.INSTANCE }
    }


}