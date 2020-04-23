rootProject.name = Bones.name
rootProject.buildFileName = "build.gradle.kts"

Projects::class.sealedSubclasses.map { it.objectInstance as Projects }.forEach {
    include(it.toString())
}
