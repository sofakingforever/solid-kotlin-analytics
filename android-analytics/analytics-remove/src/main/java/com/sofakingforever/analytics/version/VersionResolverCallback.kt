package com.sofakingforever.analytics.version

import android.util.Log
import com.sofakingforever.repoverse.Version
import com.sofakingforever.repoverse.resolvers.RemoteVersionResolver

class VersionResolverCallback(private val currentVersion: Version) : RemoteVersionResolver.Callback {

    override fun onVersionResolved(latestVersion: Version) {
        if (currentVersion < latestVersion) {
            // user should update
            Log.w("kotlin-analytics", "Latest library version $latestVersion > $currentVersion (current)")
        }
    }
}