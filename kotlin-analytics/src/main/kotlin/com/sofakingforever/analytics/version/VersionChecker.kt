package com.sofakingforever.analytics.version

import com.sofakingforever.repoverse.Version
import com.sofakingforever.repoverse.resolvers.BintrayVersionResolver
import com.sofakingforever.repoverse.resolvers.GitHubVersionResolver

object VersionChecker {

    private val currentVersion = Version("1.0.19")

    private const val packagePath: String = "sofakingforever/kotlin-analytics"

    fun invoke() {

        // we use github instead of bintray,
        // because usually that's the one that's updated after live-tests were done

        GitHubVersionResolver(packagePath)
                .resolve(VersionResolverCallback(currentVersion))

    }

}
