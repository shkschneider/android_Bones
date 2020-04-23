plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJvm)
    id(Plugins.kotlinxSerialization)
}

dependencies {
    implementation(Dependencies.KotlinStdlib)
    implementation(Dependencies.KotlinSerialization)
}
