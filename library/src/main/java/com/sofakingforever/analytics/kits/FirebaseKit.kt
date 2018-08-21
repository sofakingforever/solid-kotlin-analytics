package com.sofakingforever.analytics.kits

class FirebaseKit private constructor() : AnalyticsKit {

    private object Holder {
        val INSTANCE = FirebaseKit()
    }

    companion object {
        val instance: FirebaseKit by lazy { Holder.INSTANCE }
    }


}