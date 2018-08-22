package com.sofakingforever.analytics.kits.answers

class AnswersKit private constructor() : AnalyticsKit {
    override val name: String = "answers"

    private object Holder {
        val INSTANCE = AnswersKit()
    }

    companion object {
        val instance: AnswersKit by lazy { Holder.INSTANCE }
    }


}