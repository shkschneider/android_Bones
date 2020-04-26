package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import com.andreapivetta.kolor.red
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import me.shkschneider.cli.utils.GitCommands
import me.shkschneider.cli.utils.exit
import me.shkschneider.cli.utils.sign

class Update : CliktCommand(name = "update", help = "...") {

    private val fetch by option().flag(default = false)

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
        if (fetch) GitCommands.fetch(all = true)
        GitCommands.pull()
        if (GitCommands.rebasing()) {
            echo("They were conflicts".red())
            exit(1)
        }
        val head = GitCommands.head()
        echo("Head now at: ${head.hash} '${head.message}'")
    }

}
