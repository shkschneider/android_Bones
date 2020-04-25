package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import com.github.ajalt.clikt.core.CliktCommand
import me.shkschneider.cli.Options
import me.shkschneider.cli.utils.GitCommands
import me.shkschneider.cli.utils.sign

class Status : CliktCommand(name = "status", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        echo("Directory: ${GitCommands.dir()}")
        echo("Branch: ${GitCommands.branch()}")
        val head = GitCommands.head()
        echo("Head: ${head.hash} ${head.message}")
        val status = GitCommands.status()
        echo("Status: ${status.size.sign()}")
        if (Options.verbose) {
            status.forEach {
                echo(" ".repeat(4) + it)
            }
        }
        val ahead = GitCommands.ahead()
        echo("Ahead: ${ahead.size.sign()}")
        if (Options.verbose) {
            ahead.forEach {
                echo(" ".repeat(4) + "${it.hash} ${it.message}")
            }
        }
        val behind = GitCommands.behind()
        echo("Behind: ${behind.size.sign()}")
        if (Options.verbose) {
            behind.forEach {
                echo(" ".repeat(4) + "${it.hash} ${it.message}")
            }
        }
        val stashes = GitCommands.stashes()
        echo("Stashed: ${stashes.size}")
        if (Options.verbose) {
            stashes.forEach {
                echo(" ".repeat(4) + "${it.hash} ${it.message}")
            }
        }
        if (GitCommands.rebasing()) {
            echo("Rebasing...")
        }
    }

}
