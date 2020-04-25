package me.shkschneider.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import me.shkschneider.cli.commands.Info
import me.shkschneider.cli.commands.Publish
import me.shkschneider.cli.commands.Status
import me.shkschneider.cli.commands.Update
import me.shkschneider.cli.utils.GitCommands

class Main : CliktCommand(
    name = "cli",
    help = "Git Workflow within your Kotlin project, from the command line. In Kotlin!",
    invokeWithoutSubcommand = false,
    printHelpOnEmptyArgs = true
) {

    init {
        versionOption("0.42.0")
        subcommands(
            Info(),
            Publish(),
            Status(),
            Update()
        )
    }

    val remote: String by option(
        "--remote",
        help = "remote to lokk out for"
    ).default("origin")

    val safe: Boolean by option(
        "--safe",
        help = "does nothing that really impacts"
    ).flag(default = Options.safe)

    val verbose: Boolean by option(
        "--verbose",
        help = "gets more verbose"
    ).flag(default = Options.verbose)

    val debug: Boolean by option(
        "--debug",
        help = "enables stacktraces"
    ).flag(default = Options.debug)

    override fun aliases(): Map<String, List<String>> = mapOf(
        "up" to listOf("update"),
        "pu" to listOf("publish")
    )

    override fun run() {
        GitCommands.dir()
        Options.remote = remote
        Options.safe = safe
        Options.verbose = verbose
        Options.debug = debug
    }

}

object Application {

    @JvmStatic
    fun main(argv: Array<String>) {
        Main().main(argv)
    }

}
