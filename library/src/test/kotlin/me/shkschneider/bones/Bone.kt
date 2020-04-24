package me.shkschneider.bones

import kotlinx.serialization.Serializable

@Serializable
data class Bone(
    val name: String,
    val length: Float,
    var isBroken: Boolean
)
