plugins {
    id(Plugins.kotlinJvm) version "1.3.72"
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        plugin(Plugins.KotlinGradle)
        plugin(Plugins.KotlinSerialization)
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

//println("Gradle ${gradle.gradleVersion}")
tasks {
    wrapper {
        gradleVersion = Versions.gradle
        distributionType = Wrapper.DistributionType.BIN
    }
}
