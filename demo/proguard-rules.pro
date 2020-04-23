-keepattributes SourceFile,LineNumberTable

# https://github.com/Kotlin/kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class me.shkschneider.bones.**$$serializer { *; }
-keepclassmembers class me.shkschneider.bones.** { *** Companion; }
-keepclasseswithmembers class me.shkschneider.bones.** { kotlinx.serialization.KSerializer serializer(...); }
