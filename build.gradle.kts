buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        plugin(Plugins.KotlinGradle)
        plugin(Plugins.AndroidGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.java
        }
    }
}
