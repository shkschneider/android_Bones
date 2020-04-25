package me.shkschneider.cli.utils

import kotlin.system.exitProcess

internal fun exit(status: Int = 0) {
    exitProcess(status)
}
