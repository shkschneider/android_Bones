package me.shkschneider.bones

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(Parameterized::class)
class MedicTests(
    private val bone: Bone?
) {

    private val serializer = Bone.serializer()

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun parameters() = listOf(
            null,
            Bone("Clavicle", 1.toFloat(), isBroken = false),
            Bone("Arm", 2.toFloat(), isBroken = false),
            Bone("Wrist", 3.toFloat(), isBroken = true),
            Bone("Hip", 4.toFloat(), isBroken = false),
            Bone("Ankle", 5.toFloat(), isBroken = false),
            Bone("Foot", 6.toFloat(), isBroken = false),
            Bone("Toe", 7.toFloat(), isBroken = false),
            Bone("Hand", 8.toFloat(), isBroken = true),
            Bone("Finger", 8.toFloat(), isBroken = false),
            Bone("Leg", 9.toFloat(), isBroken = false)
        )

    }

    @Test
    fun value() {
        val medic = Medik(serializer, bone)
        assertEquals(bone, medic.value)
        medic.value = bone
        assertEquals(bone, medic.value)
    }

    @Test
    fun `toString matches value`() {
        val medic = Medik(Bone.serializer(), bone)
        if (bone == null) assertEquals("", medic.toString())
        else assertNotNull(medic.toString())
    }

    @Test
    fun `plaster writes to memory`() {
        val medic = Medik(Bone.serializer(), bone)
        medic.plaster(medic.value)
        if (bone == null) assertNull(medic.memory)
        else assertNotNull(medic.memory)
    }

    @Test
    fun `wiped means no memory nor value`() {
        val medic = Medik(Bone.serializer(), bone)
        medic.value = null
        assertNull(medic.memory)
        assertNull(medic.value)
        assertEquals("", medic.toString())
    }

    @Test
    fun `repair restores from memory`() {
        val medic = Medik(Bone.serializer(), bone)
        medic.value = null
        medic.repair(when (bone) {
            null -> ""
            else -> """{"name":"${bone.name}","length":${bone.length},"isBroken":${bone.isBroken}}"""
        })
        assertEquals(bone != null, medic.repair())
        assertEquals(bone != null, medic.memory != null)
        assertEquals(bone != null, medic.value != null)
        assertEquals(bone, medic.value)
    }

}
