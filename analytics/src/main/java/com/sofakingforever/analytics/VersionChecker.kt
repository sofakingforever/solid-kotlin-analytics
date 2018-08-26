package com.sofakingforever.analytics

import android.util.Log
import com.sofakingforever.library.BuildConfig
import okhttp3.*
import java.io.IOException

object VersionChecker {
    fun onCheckVersion(analytics: Analytics) {

        val client = OkHttpClient.Builder()
                .followRedirects(true)
                .build()

        val request = Request.Builder()
                .url("https://bintray.com/sofakingforever/analytics/kotlin-analytics/_latestVersion")
                .get()
                .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call?, e: IOException?) {
                // ignore failure, no need to fill logcat with bullshit
            }

            override fun onResponse(call: Call?, response: Response?) {
                // try to finish up
                response?.networkResponse()?.request()?.url().also {

                    val latestVersion = extractVersion(it)

                    if (latestVersion?.contains('.') == true) {

                        if (latestVersion.contentEquals(BuildConfig.VERSION_NAME)) {
                            // all is good
                        } else {
                            Log.w("Analytics", las)

                        }


                    }
                }
            }

        })
    }

    fun extractVersion(it: HttpUrl?) = it?.encodedPathSegments()?.last()

}