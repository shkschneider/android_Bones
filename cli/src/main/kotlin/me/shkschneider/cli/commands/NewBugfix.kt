package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import me.shkschneider.cli.utils.GitCommands

class NewBugfix : New(name = "new-bugfix", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        GitCommands.new("bugfix/$name", parent, push)
    }

}
