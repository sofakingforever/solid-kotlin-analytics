package com.sofakingforever.analytics.version

import com.sofakingforever.library.BuildConfig
import com.sofakingforever.repoverse.Version
import com.sofakingforever.repoverse.resolvers.BintrayVersionResolver

object VersionChecker {

    private val currentVersion = Version(BuildConfig.VERSION_NAME)

    private const val packagePath: String = "sofakingforever/analytics/kotlin-analytics"

    fun invoke() {
        BintrayVersionResolver(packagePath)
                .resolve(VersionResolverCallback(currentVersion))

    }

}
