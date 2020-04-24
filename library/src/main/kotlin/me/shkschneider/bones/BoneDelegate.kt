package me.shkschneider.bones

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement
import kotlin.reflect.KProperty

class BoneDelegate<T : Any>(private val serializer: KSerializer<T>) {

    companion object {

        private val provider = Json(JsonConfiguration.Stable)

    }

    private var json: JsonElement? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? =
        jsonToObject(json)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        json = objectToJson(value)
    }

//    private fun stringToJson(string: String?): JsonElement? =
//        string?.let {
//            provider.parseJson(it)
//        }

    private fun jsonToObject(jsonElement: JsonElement?): T? =
        jsonElement?.let {
            provider.fromJson(serializer, it)
        }

    private fun objectToJson(obj: T?): JsonElement? =
        obj?.let {
            provider.toJson(serializer, it)
        }

    override fun toString(): String = json.toString()

}
