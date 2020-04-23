plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Android.compile)
    defaultConfig {
        applicationId = Bones.id
        minSdkVersion(Android.min)
        targetSdkVersion(Android.target)
        versionCode = Bones.versionCode
        versionName = Bones.version
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Dependencies.AndroidAppCompat)
    implementation(Dependencies.AndroidConstraintLayout)
    implementation(Dependencies.AndroidCoreKtx)
    implementation(Dependencies.GoogleMaterial)
    implementation(Dependencies.KotlinStdlib)
    implementation(Projects.Library)
}
