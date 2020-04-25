package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import com.andreapivetta.kolor.red
import com.github.ajalt.clikt.core.CliktCommand
import me.shkschneider.cli.Options
import me.shkschneider.cli.utils.GitCommands
import me.shkschneider.cli.utils.exit
import me.shkschneider.cli.utils.sign

class Publish : CliktCommand(name = "publish", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        if (GitCommands.rebasing()) {
            echo("Rebase in progress. Abort.")
            exit(1)
        }
        GitCommands.status().let { status ->
            if (status.isNotEmpty()) {
                echo("Directory is not clean: ${status.size.sign()}".red())
                exit(1)
            } else {
                echo("Directory is clean")
            }
        }
        GitCommands.fetch(all = false)
        val ahead = GitCommands.ahead()
        if (ahead.isEmpty()) {
            echo("Nothing to push")
            exit(0)
        } else {
            val behind = GitCommands.behind()
            if (behind.isNotEmpty()) {
                if (Options.verbose) echo("Behind by ${behind.size}. Updating...")
                if (!Options.safe) if (!GitCommands.pull(rebase = true)) exit(1)
            }
            echo("Pushing:")
            ahead.forEach {
                echo(" ".repeat(4) + " ${it.hash} ${it.message}")
            }
            if (!Options.safe) GitCommands.push()
            echo("Remote '${GitCommands.remotes().first()}/${GitCommands.branch()}' updated.")
        }
    }

}
