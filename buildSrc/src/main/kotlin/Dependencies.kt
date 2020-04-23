import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

sealed class Dependencies(
    private val group: String,
    private val name: String,
    private val version: String
) {

    object KotlinStdlib : Dependencies("org.jetbrains.kotlin", "kotlin-stdlib", Versions.kotlin)
    object AndroidAppCompat : Dependencies("androidx.appcompat", "appcompat","1.1.0")
    object AndroidConstraintLayout : Dependencies("androidx.constraintlayout", "constraintlayout", "1.1.3")
    object AndroidCoreKtx : Dependencies("androidx.core", "core-ktx" , "1.2.0")
    object GoogleMaterial : Dependencies("com.google.android.material", "material", "1.1.0")

    override fun toString(): String = "$group:$name:$version"

}

fun DependencyHandler.api(dependency: Dependencies): Dependency? =
    add("api", dependency.toString())

fun DependencyHandler.implementation(dependency: Dependencies): Dependency? =
    add("implementation", dependency.toString())

fun DependencyHandler.testImplementation(dependency: Dependencies): Dependency? =
    add("testImplementation", dependency.toString())
