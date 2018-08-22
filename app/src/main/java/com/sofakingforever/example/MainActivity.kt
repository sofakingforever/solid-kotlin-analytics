package com.sofakingforever.example

import android.os.Bundle
import com.sofakingforever.analytics.kits.answers.AnswersKit
import com.sofakingforever.example.events.EventPerKit
import com.sofakingforever.example.events.MainContentView
import com.sofakingforever.example.events.ParameterizedEvent
import com.sofakingforever.example.events.SimpleEvent
import com.sofakingforever.kotlin_analytics.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    private var isFirstClick: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        checkbox_optout.setOnCheckedChangeListener { button, isChecked ->
            // update the settings to enable or disable all analytics tools
            analytics.settings.isAnalyticsEnabled = isChecked

        }

        button_eventSimple.setOnClickListener {

            analytics.track(SimpleEvent())

        }


        button_eventKit.setOnClickListener {

            analytics.track(EventPerKit(true))

        }

        button_eventParams.setOnClickListener {

            // don't track event on first click
            if (isFirstClick) {
                analytics.setKitEnabled(AnswersKit.instance, false)
            }

            analytics.track(ParameterizedEvent(isFirstClick, System.currentTimeMillis()))

            // but turn in back on for later
            if (isFirstClick) {
                analytics.setKitEnabled(AnswersKit.instance, true)
            }

            isFirstClick = false

        }


    }

    override fun onStart() {
        super.onStart()

        analytics.track(MainContentView())
    }
}