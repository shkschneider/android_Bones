plugins {
    id(Plugins.gradleApplication)
    id(Plugins.kotlin)
    id(Plugins.kotlinJvm)
    id(Plugins.kotlinSerialization)
}

application {
    applicationName = rootProject.name
    mainClassName = "me.shkschneider.bones.Application"
}

dependencies {
    implementation(Dependencies.KotlinStdlib)
    implementation(Dependencies.KotlinxSerialization)
    implementation(Projects.Library)
}
