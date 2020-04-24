package me.shkschneider.bones

import kotlinx.serialization.json.JsonConfiguration

object Application {

    @JvmStatic
    fun main(vararg argv: String) {
        println("Bones!")
        Medik.jsonConfiguration = JsonConfiguration.Stable.copy(prettyPrint = false)
        val bone = Bone(
            name = "Wrist",
            length = 3.toFloat(),
            isBroken = false,
            description = "Lorem ipsum."
        )
        with(Medik(Bone.serializer(), bone)) {
            println("What my bone looks like: $value")
            println("Ouch... just broke my bone")
            value = bone.copy(isBroken = true)
            println("What my bone now looks like: $value")
            value = bone.copy(isBroken = false)
            println("What plastering made my bone now look like: $value")
            when (value?.isBroken) {
                false -> println("Feeling happy")
                else -> println("Feeling sad")
            }
        }
    }

}
