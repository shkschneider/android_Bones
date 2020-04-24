package me.shkschneider.bones

import kotlinx.serialization.json.JsonElement
import java.io.File
import java.nio.charset.Charset

class DiskMedicine(
    private val file: File = createTempFile()
) : Medicine() {

    @Suppress("MemberVisibilityCanBePrivate")
    internal var data: JsonElement? = null

    override fun read(): String? =
        file.readText(Charset.defaultCharset()).takeIf { it.isNotEmpty() }

    override fun write(string: String?) {
        file.writeText(string.orEmpty(), Charset.defaultCharset())
    }

}
