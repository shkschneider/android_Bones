import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.kotlin.dsl.repositories

fun ScriptHandler.repositories() =
    repositories {
        jcenter()
        mavenCentral()
    }
