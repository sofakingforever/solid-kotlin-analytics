package com.sofakingforever.analytics.version

import com.sofakingforever.analytics.exceptions.IllegalVersionFormatException
import okhttp3.*
import java.io.IOException

// "sofakingforever/analytics/kotlin-analytics"
class BintrayVersionResolver(override val packagePath: String) : RemoteVersionResolver {
    override fun resolve(callback: RemoteVersionResolver.Callback) {

        val client = OkHttpClient.Builder()
                .followRedirects(true)
                .build()

        val request = Request.Builder()
                .url("https://bintray.com/$packagePath/_latestVersion")
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
                        val latestVersion = Version(url?.encodedPathSegments()?.last() ?: "")

                        callback.onVersionResolved(latestVersion)
                    } catch (e: IllegalVersionFormatException) {
                        // ignore failure, no need to fill logcat with bullshit
                    }


                }
            }

        })
    }


    private fun Response?.url(): HttpUrl? {
        return this?.networkResponse()?.request()?.url()
    }

}