package com.sofakingforever.analytics

class EnabledMap<Key> : LinkedHashMap<Key, Boolean>() {

    fun isDisabled(something : Key) : Boolean = this[something] == false

}