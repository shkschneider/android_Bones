plugins {
    id(Plugins.base)
}

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        plugin(Plugins.KotlinGradle)
        plugin(Plugins.KotlinSerialization)
    }
}

//println("Gradle ${gradle.gradleVersion}")
tasks {
    wrapper {
        gradleVersion = Versions.gradle
        distributionType = Wrapper.DistributionType.BIN
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
    pluginManager.withPlugin(Plugins.kotlin) {
        dependencies {
            implementation(Dependencies.KotlinStdlib)
        }
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = Versions.java
}

repositories {
    jcenter()
    mavenCentral()
}
