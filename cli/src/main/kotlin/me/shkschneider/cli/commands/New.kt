package me.shkschneider.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import me.shkschneider.cli.Options
import me.shkschneider.cli.utils.GitCommands

abstract class New(name: String, help: String) : CliktCommand(name = name, help = help) {

    protected val parent: String by option(
        "--parent",
        help = "base for new branch"
    ).default(GitCommands.branch())

    protected val push: Boolean by option(
        "--push",
        help = "published on remote"
    ).flag(default = Options.dryRun)

    protected val name by argument(name = "name", help = "...")

}
