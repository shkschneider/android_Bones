package me.shkschneider.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import me.shkschneider.cli.commands.Info
import me.shkschneider.cli.commands.NewBugfix
import me.shkschneider.cli.commands.NewFeature
import me.shkschneider.cli.commands.NewHotfix
import me.shkschneider.cli.commands.NewRelease
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
            NewBugfix(),
            NewFeature(),
            NewHotfix(),
            NewRelease(),
            Publish(),
            Status(),
            Update()
        )
    }

    val remote: String by option(
        "--remote",
        help = "remote to look out for"
    ).default("origin")

    val dryRun: Boolean by option(
        "--dry-run",
        help = "does nothing that really impacts"
    ).flag(default = Options.dryRun)

    val verbose: Boolean by option(
        "--verbose",
        help = "gets more verbose"
    ).flag(default = Options.verbose)

    val debug: Boolean by option(
        "--debug",
        help = "enables stacktraces"
    ).flag(default = Options.debug)

    override fun aliases(): Map<String, List<String>> = mapOf(
        "pu" to listOf("publish"),
        "nb" to listOf("new-bugfix"),
        "nf" to listOf("new-feature"),
        "nh" to listOf("new-hotfix"),
        "nr" to listOf("new-release"),
        "up" to listOf("update")
    )

    override fun run() {
        GitCommands.dir()
        Options.remote = remote
        Options.dryRun = dryRun
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
