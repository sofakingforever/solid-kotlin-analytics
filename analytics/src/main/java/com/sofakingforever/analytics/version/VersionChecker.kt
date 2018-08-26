package com.sofakingforever.analytics.version

import android.util.Log
import com.sofakingforever.analytics.exceptions.IllegalVersionFormatException
import com.sofakingforever.library.BuildConfig
import okhttp3.*
import java.io.IOException

object VersionChecker {

    fun onCheckVersion() {

        val currentVersion = Version(BuildConfig.VERSION_NAME)

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
                response?.url().also { url ->

                    try {
                        val latestVersion = Version(extractVersion(url))

                        if (currentVersion < latestVersion) {
                            // user should update
                            Log.w("Analytics", "Latest kotlin-analytics version is $latestVersion > $currentVersion")
                        }
                    } catch (e: IllegalVersionFormatException) {
                        // ignore failure, no need to fill logcat with bullshit
                    }


                }
            }

        })
    }

    fun extractVersion(it: HttpUrl?) : String = it?.encodedPathSegments()?.last() ?: ""

    private fun Response?.url(): HttpUrl? {
        return this?.networkResponse()?.request()?.url()
    }

}
