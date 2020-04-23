import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

sealed class Projects(private val name: String) {

    object Demo : Projects("demo")
    object Library : Projects("library")

    override fun toString(): String = ":$name"

}

fun DependencyHandler.api(project: Projects): Dependency? =
    add("api", project(mapOf("path" to project.toString())))

fun DependencyHandler.implementation(project: Projects): Dependency? =
    add("implementation", project(mapOf("path" to project.toString())))

fun DependencyHandler.testImplementation(project: Projects): Dependency? =
    add("testImplementation", project(mapOf("path" to project.toString())))
