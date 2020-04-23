import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

sealed class Plugins(
    private val group: String,
    private val name: String,
    private val version: String
) {
    companion object {

        const val base = "base"
        const val gradleApplication = "org.gradle.application"
        const val javaLibrary = "java-library"
        const val kotlin = "kotlin"
        const val kotlinJvm = "org.jetbrains.kotlin.jvm"
        const val kotlinSerialization = "org.jetbrains.kotlin.plugin.serialization"

    }

    object KotlinGradle : Plugins("org.jetbrains.kotlin", "kotlin-gradle-plugin", Versions.kotlin)
    object KotlinSerialization : Plugins("org.jetbrains.kotlin", "kotlin-serialization", Versions.kotlin)

    override fun toString(): String = "$group:$name:$version"

}

fun DependencyHandlerScope.plugin(plugin: Plugins): Dependency? =
    add("classpath", plugin.toString())
