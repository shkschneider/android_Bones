package me.shkschneider.cli.utils

import java.io.File

internal fun file(dir: String, file: String) =
    File(listOf(dir, file).joinToString(File.separator))
