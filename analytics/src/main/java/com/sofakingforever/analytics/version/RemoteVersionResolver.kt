package com.sofakingforever.analytics.version

interface RemoteVersionResolver {

    val packagePath: String

    fun resolve(callback: Callback)

    interface Callback {
        fun onVersionResolved(latestVersion: Version)
    }

}

