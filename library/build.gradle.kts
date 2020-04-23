plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlin)
    id(Plugins.kotlinJvm)
    id(Plugins.kotlinSerialization)
}

dependencies {
    implementation(Dependencies.KotlinStdlib)
    implementation(Dependencies.KotlinxSerialization)
}
