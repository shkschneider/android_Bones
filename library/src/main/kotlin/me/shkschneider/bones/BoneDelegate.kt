package me.shkschneider.bones

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.reflect.KProperty

class BoneDelegate<T : Any>(private val serializer: KSerializer<T>) {

    private val provider = Json(JsonConfiguration.Stable)
    private var json: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? =
        json?.let {
            val jsonElement = provider.parseJson(it)
            provider.fromJson(serializer, jsonElement)
        }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        json = value?.let {
            provider.toJson(serializer, value).toString()
        }
    }

}
