package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@Route(name = "removeplayer")
@Permission("byzuscells.removeplayer")
public class RemovePlayerCommand {

    /*
    /removeplayer Byzus
     */

    @Execute(required = 1)
    void execute(CommandSender sender, @Arg @Name("target") String target) {
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            sender.sendMessage(Component.text("Nie znaleziono gracza o nicku " + target));
            return;
        }
        UUID uuid = player.getUniqueId();

        CellManager.removePlayer(sender, uuid);
        sender.sendMessage(Component.text("Pomyślnie usunięto gracza z celi"));
    }
}
