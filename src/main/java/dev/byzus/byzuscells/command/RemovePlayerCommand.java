package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

@Route(name = "removeplayer")
public class RemovePlayerCommand {

    /*
    /removeplayer Byzus
     */

    @Execute(required = 2)
    void execute(Player sender, @Arg @Name("uuid") UUID target) {
        CellManager.removePlayer(target);
        sender.sendMessage(Component.text(ChatColor.GREEN + "Pomyślnie usunięto gracza z celi"));
    }
}
