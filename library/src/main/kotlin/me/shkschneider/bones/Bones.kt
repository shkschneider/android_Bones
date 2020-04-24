package me.shkschneider.bones

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement

class Bones<T : Any>(
    private val serializer: KSerializer<T>
) {

    companion object {

        private val provider = Json(JsonConfiguration.Stable)

    }

    private var json: JsonElement? = null

    fun plaster(value: T?) {
        json = objectToJson(value)
    }

    fun restore(): T? =
        jsonToObject(json)

    private fun jsonToObject(jsonElement: JsonElement?): T? =
        jsonElement?.let {
            provider.fromJson(serializer, it)
        }

    private fun objectToJson(obj: T?): JsonElement? =
        obj?.let {
            provider.toJson(serializer, it)
        }

}
