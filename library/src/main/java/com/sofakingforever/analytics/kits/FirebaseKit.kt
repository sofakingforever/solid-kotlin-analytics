package com.sofakingforever.analytics.kits

class FirebaseKit private constructor() : AnalyticsKit {

    override val name: String = "firebase"

    private object Holder {
        val INSTANCE = FirebaseKit()
    }

    companion object {
        val instance: FirebaseKit by lazy { Holder.INSTANCE }
    }


}