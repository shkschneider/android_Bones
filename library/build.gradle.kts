plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJvm)
}

dependencies {
    implementation(Dependencies.KotlinStdlib)
    implementation(Dependencies.KotlinSerialization)
}
