package com.sofakingforever.analytics.version

import android.util.Log
import com.sofakingforever.library.BuildConfig

object VersionChecker {

    val currentVersion = Version(BuildConfig.VERSION_NAME)

    private val callback: RemoteVersionResolver.Callback = object : RemoteVersionResolver.Callback {
        override fun onVersionResolved(latestVersion: Version) {
            if (currentVersion < latestVersion) {
                // user should update
                Log.w("kotlin-analytics", "Latest library version $latestVersion > $currentVersion (current)")
            }
        }

    }

    fun invoke() {
        BintrayVersionResolver("sofakingforever/analytics/kotlin-analytics").resolve(callback)

    }

}
