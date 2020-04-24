package me.shkschneider.bones

import java.util.UUID

abstract class Medicine {

    protected val id: UUID = UUID.randomUUID()

    abstract fun read(): String?

    abstract fun write(string: String?)

}
