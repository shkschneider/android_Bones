package me.shkschneider.bones

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonException
import kotlin.properties.Delegates

class Medik<T : Any>(
    internal val medicine: Medicine = DiskMedicine(),
    private val serializer: KSerializer<T>,
    value: T? = null
) {

    companion object {

        private val json by lazy {
            Json(JsonConfiguration.Stable)
        }

    }

    internal var memory: JsonElement? = null
    var value by Delegates.observable(value) { _ /* property */, _ /* old */, new ->
        plaster(new)
    }

    internal fun plaster(new: T?) {
        try {
            medicine.write(new?.let { json.toJson(serializer, it) }?.toString())
        } catch (e: JsonException) {
            TODO("JsonException: ${e.message}")
        } catch (e: SerializationException) {
            TODO("SerializationException: ${e.message}")
        }
    }

    internal fun repair(string: String?): Boolean =
        try {
            medicine.write(string)
            repair()
        } catch (e: Exception) {
            false
        }

    fun repair(): Boolean {
        return try {
            value = medicine.read()?.let { json.fromJson(serializer, json.parseJson(it)) }
            value != null
        } catch (e: JsonException) {
            false
        } catch (e: SerializationException) {
            false
        }
    }

    override fun toString(): String = value?.toString().orEmpty()

}
