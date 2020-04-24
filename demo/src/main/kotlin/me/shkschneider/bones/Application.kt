package me.shkschneider.bones

object Application {

    private var bone by BoneDelegate(Bone.serializer())
    private val clavicle = Bone.clavicle

    @JvmStatic
    fun main(vararg argv: String) {
        println("Bones!")
        println("What I start with: $clavicle")
        bone = clavicle
        println("What my bone looks like: $bone")
        bone = null
        bone = clavicle
        println("What my bone still looks like: $bone")
        bone?.isBroken = true
        println("What my broken bone looks like: $bone")
    }

}
