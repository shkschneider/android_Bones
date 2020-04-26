package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import me.shkschneider.cli.utils.GitCommands

class NewRelease : New(name = "new-release", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        GitCommands.new("release/$name", parent, push)
    }

}
