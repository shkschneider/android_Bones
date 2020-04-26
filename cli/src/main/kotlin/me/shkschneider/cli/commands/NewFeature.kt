package me.shkschneider.cli.commands

import com.andreapivetta.kolor.lightYellow
import me.shkschneider.cli.utils.GitCommands

class NewFeature : New(name = "new-feature", help = "...") {

    override fun run() {
        echo(commandName.capitalize().lightYellow())
        GitCommands.new("feature/$name", parent, push)
    }

}
