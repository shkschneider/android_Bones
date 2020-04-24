package me.shkschneider.bones

object Application {

    private val bones = Bones(Bone.serializer())
    private val clavicle = Bone.clavicle

    @JvmStatic
    fun main(vararg argv: String) {
        println("Bones!")
        var bone: Bone? = clavicle
        println("What I start with: $clavicle")
        println("What my bone looks like: $bone")
        println("I'm plastering my bone")
        bones.plaster(bone)
        println("Ouch... just broke my bone")
        bone?.isBroken = true
        println("What my bone now looks like: $bone")
        bone = bones.restore()
        println("What my plaster bone looks like: $bone")
        when (bone?.isBroken) {
            false -> println("Feeling happy")
            else -> println("Feeling sad")
        }
    }

}
