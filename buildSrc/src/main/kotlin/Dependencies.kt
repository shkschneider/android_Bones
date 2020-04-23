import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

sealed class Dependencies(
    private val group: String,
    private val name: String,
    private val version: String
) {

    object KotlinStdlib : Dependencies("org.jetbrains.kotlin", "kotlin-stdlib", Versions.kotlin)
    object KotlinxSerialization : Dependencies("org.jetbrains.kotlinx", "kotlinx-serialization-runtime", "0.20.0")

    override fun toString(): String = "$group:$name:$version"

}

fun DependencyHandler.api(dependency: Dependencies): Dependency? =
    add("api", dependency.toString())

fun DependencyHandler.implementation(dependency: Dependencies): Dependency? =
    add("implementation", dependency.toString())

fun DependencyHandler.testImplementation(dependency: Dependencies): Dependency? =
    add("testImplementation", dependency.toString())
