package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.manager.GUIManager;
import dev.byzus.byzuscells.manager.PlayerJailManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "jail")
@Permission("byzuscells.jail")
public class JailPlayerCommand {

    @Execute(required = 2)
    void execute(CommandSender sender, @Arg @Name("target") Player target, @Arg @Name("radius") int borderSize) {
        if (target == null) {
            sender.sendMessage(Components.error("Cannot find player with name: ").append(Component.text(target.getName())));
        }

        if (borderSize <= 0) {
            sender.sendMessage(Components.error("Border size can't be 0 or less."));
        }

        if (PlayerJailManager.jails.values().contains(target.getUniqueId())) {
            sender.sendMessage(Components.success("Player has been jailed."));
        }
        PlayerJailManager.jail(sender, target, borderSize);
    }

    @Execute(required = 0)
    void executeGUI(CommandSender sender) {
        Player target = (Player) sender;
        GUIManager.jailGUI(target);
    }

}
