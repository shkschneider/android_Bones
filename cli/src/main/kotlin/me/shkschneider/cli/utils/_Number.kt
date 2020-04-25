package me.shkschneider.cli.utils

internal fun Number.sign() =
    when {
        this == 0 -> "$this"
        toInt() < 0 -> "-$this"
        else -> "+$this"
    }
