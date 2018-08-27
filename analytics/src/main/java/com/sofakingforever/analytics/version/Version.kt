package com.sofakingforever.analytics.version

import com.sofakingforever.analytics.exceptions.IllegalVersionFormatException

class Version : Comparable<Version> {

    private val version: String

    @Throws(IllegalVersionFormatException::class) constructor(version: String) {
        if (!version.contains(".")) throw IllegalVersionFormatException(version)
        this.version = version
    }

    override fun toString(): String {
        return version
    }

    override fun compareTo(other: Version): Int {

        val levels1 = this.version.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val levels2 = other.version.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val length = Math.max(levels1.size, levels2.size)

        for (i in 0 until length) {
            val v1 = if (i < levels1.size) Integer.parseInt(levels1[i]) else 0
            val v2 = if (i < levels2.size) Integer.parseInt(levels2[i]) else 0
            val compare = v1.compareTo(v2)
            if (compare != 0) {
                return compare
            }
        }

        return 0
    }

}
