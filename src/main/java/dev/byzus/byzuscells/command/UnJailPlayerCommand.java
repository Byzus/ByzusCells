package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.controller.PlayerJailController;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "unjail")
@Permission("byzuscells.unjail")
public class UnJailPlayerCommand {

    @Execute(required = 1)
    void execute(CommandSender sender, @Arg @Name("target") Player target) {
        PlayerJailController.unJail(sender, target);
    }

}
