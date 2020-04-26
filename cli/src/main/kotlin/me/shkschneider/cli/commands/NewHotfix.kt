package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import me.shkschneider.cli.utils.GitCommands

class NewHotfix : New(name = "new-hotfix", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        GitCommands.new("hotfix/$name", parent, push)
    }

}
