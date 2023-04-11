package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.manager.PlayerJailManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "jail")
@Permission("byzuscells.jail")
public class JailPlayerCommand {

    @Execute(required = 2)
    void execute(CommandSender sender, @Arg @Name("target") Player target, @Arg @Name("radius") int borderSize) {
        PlayerJailManager.jail(sender, target, borderSize);
    }

}
