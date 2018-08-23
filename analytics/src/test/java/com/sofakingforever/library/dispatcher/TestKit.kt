package com.sofakingforever.library.dispatcher

import com.sofakingforever.analytics.AnalyticsKit

class TestKit private constructor() : AnalyticsKit {
    override val name: String = "Test Kit"


    private object Holder {
        val INSTANCE = TestKit()
    }

    companion object {
        val instance: TestKit by lazy { Holder.INSTANCE }
    }


}