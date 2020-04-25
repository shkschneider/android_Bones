package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import com.github.ajalt.clikt.core.CliktCommand
import me.shkschneider.cli.Options
import me.shkschneider.cli.utils.GitCommands
import java.io.File

class Info : CliktCommand(name = "info", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        val dir = File(GitCommands.dir()).toPath()
        echo("Project: ${dir.fileName}")
        val (birthHash, _, birthDate) = GitCommands.birth()
        echo("Born: $birthDate ($birthHash)")
        val (authorName, authorEmail) = GitCommands.author()
        echo("Author: $authorName <$authorEmail>")
        echo("Authors: ${GitCommands.authors().size}")
        if (Options.verbose) {
            val config = File(GitCommands.dir() + File.separator + "config")
            echo("Config: ${config.absoluteFile}")
            config.useLines { line ->
                line.filter { it.isNotBlank() }.map { it.trimStart() }.forEach {
                    echo(" ".repeat(if (it.startsWith("[")) 4 else 8) + it)
                }
            }
        }
    }

}
