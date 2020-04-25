package me.shkschneider.cli.utils

import com.andreapivetta.kolor.cyan
import com.github.ajalt.clikt.output.TermUi.echo
import me.shkschneider.cli.Options
import java.util.Scanner

private fun Scanner.nextLines(maxLines: Int? = null): List<String> = mutableListOf<String>().apply {
    while (hasNext()) {
        add(nextLine())
        if (size == maxLines) break
    }
}

internal fun exec(commandLine: String, result: ((Result<List<String>>) -> Unit)? = null): Int {
    try {
        if (Options.debug) echo(commandLine.cyan())
        val process = Runtime.getRuntime().exec(commandLine)
        process.waitFor()
        val status = process.exitValue()
        if (status != 0) throw RuntimeException("Exited with code $status: $commandLine")
        if (result != null) {
            Scanner(process.inputStream).use {
                result(Result.success(it.nextLines()))
            }
        }
        return status
    } catch (e: Exception) {
        if (Options.debug) echo(e.toString().cyan())
        result?.invoke(Result.failure(e))
        return Short.MAX_VALUE.toInt()
    }
}
