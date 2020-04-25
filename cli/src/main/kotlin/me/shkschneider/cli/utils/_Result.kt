package me.shkschneider.cli.utils

import com.andreapivetta.kolor.red
import com.github.ajalt.clikt.output.TermUi
import me.shkschneider.cli.Options

internal fun <T> Result<T>.onFailure(status: Int = 1) {
    exceptionOrNull()?.let { throwable ->
        if (Options.debug) {
            TermUi.echo(throwable.localizedMessage.red())
            throwable.printStackTrace()
        } else {
            TermUi.echo("${throwable::class.simpleName}: ${throwable.localizedMessage}".red())
        }
        exit(status)
    }
}
