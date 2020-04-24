package me.shkschneider.bones

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(Parameterized::class)
class MedicTests(
    pair: Pair<Medicine, Bone?>
) {

    private val medicine: Medicine = pair.first
    private val bone: Bone? = pair.second
    private val serializer = Bone.serializer()

    companion object {

        private val medicines = listOf(
            MemoryMedicine(),
            DiskMedicine()
        )
        private val bones = listOf(
            null,
            // 10 most broken bones
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

        @Parameterized.Parameters
        @JvmStatic
        fun parameters() = mutableListOf<Pair<Medicine, Bone?>>().apply {
            medicines.forEach { medicine ->
                bones.forEach { bone ->
                    add(medicine to bone)
                }
            }
        }.toList()

    }

    @Test
    fun value() {
        val medic = Medik(medicine, serializer, bone)
        assertEquals(bone, medic.value)
        medic.value = bone
        assertEquals(bone, medic.value)
    }

    @Test
    fun `toString matches value`() {
        val medic = Medik(medicine, serializer, bone)
        if (bone == null) assertEquals("", medic.toString())
        else assertNotNull(medic.toString())
    }

    @Test
    fun `plaster writes to memory`() {
        val medic = Medik(medicine, serializer, bone)
        medic.plaster(medic.value)
        if (bone == null) assertNull(medic.medicine.read())
        else assertNotNull(medic.medicine.read())
    }

    @Test
    fun `wiped means no memory nor value`() {
        val medic = Medik(medicine, serializer, bone)
        medic.value = null
        assertNull(medic.medicine.read())
        assertNull(medic.value)
        assertEquals("", medic.toString())
    }

    @Test
    fun `repair restores from memory`() {
        val medic = Medik(medicine, serializer, bone)
        medic.value = null
        medic.repair(
            when (bone) {
                null -> ""
                else -> """{"name":"${bone.name}","length":${bone.length},"isBroken":${bone.isBroken}}"""
            }
        )
        assertEquals(bone != null, medic.repair())
        assertEquals(bone != null, medic.medicine.read() != null)
        assertEquals(bone != null, medic.value != null)
        assertEquals(bone, medic.value)
    }

}
