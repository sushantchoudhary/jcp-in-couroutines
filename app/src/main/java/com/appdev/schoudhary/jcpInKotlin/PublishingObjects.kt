package com.appdev.schoudhary.jcpInKotlin

//@Don'tDoIt
object PublishingObjects {
    data class Secret(val id: Int)
    var knownSecrets = emptySet<Secret>()

    fun initialize() {
        knownSecrets = hashSetOf()
    }
}