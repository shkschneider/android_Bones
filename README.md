# android_Bones

```
buildscript {
    dependencies {
        // ...
        classpath("org.jetbrains.kotlin:kotlin-serialization:1+)
    }
}

```

```
plugins {
    // ...
    id("kotlinx-serialization")
}
dependencies {
    // ...
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
}
```

```
import kotlinx.serialization.Serializable

@Serializable
data class Bone(
    val name: String,
    val length: Float,
    var isBroken: Boolean
)
```

