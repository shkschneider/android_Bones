package me.shkschneider.bones

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable
import me.shkschneider.bones.serializers.UuidSerializer
import java.util.UUID

@Serializable
data class Bone(
    @Serializable(with = UuidSerializer::class)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val length: Float,
    var isBroken: Boolean,
    @ContextualSerialization
    val description: Any? = null
)
