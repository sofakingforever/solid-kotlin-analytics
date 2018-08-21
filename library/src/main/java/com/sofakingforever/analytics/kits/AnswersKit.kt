package com.sofakingforever.analytics.kits

class AnswersKit private constructor() : AnalyticsKit {

    private object Holder {
        val INSTANCE = AnswersKit()
    }

    companion object {
        val instance: AnswersKit by lazy { Holder.INSTANCE }
    }


}