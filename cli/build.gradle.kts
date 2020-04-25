plugins {
    id(Plugins.gradleApplication)
    id(Plugins.kotlin)
    id(Plugins.kotlinJvm)
    id(Plugins.kotlinSerialization)
}

application {
    applicationName = "cli"
    mainClassName = "me.shkschneider.cli.Application"
}

dependencies {
    implementation(Dependencies.KotlinStdlib)
    implementation(Dependencies.CliKt)
    implementation(Dependencies.Kolor)
}

tasks.withType(Sync::class) {
    destinationDir = File("$rootDir/build/cli")
}
tasks.alias("cli", "installDist")

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = Versions.java
}
