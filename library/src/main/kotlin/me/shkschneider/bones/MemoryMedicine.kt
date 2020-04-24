package me.shkschneider.bones

class MemoryMedicine : Medicine() {

    @Suppress("MemberVisibilityCanBePrivate")
    internal var data: String? = null

    override fun read(): String? =
        data.takeIf { it?.isNotEmpty() == true }

    override fun write(string: String?) {
        data = string.takeIf { it?.isNotEmpty() == true }
    }

}
