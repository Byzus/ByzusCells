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

@Route(name = "addplayer")
@Permission("byzuscells.addplayer")
public class AddPlayerCommand {

    /*
    /addplayer Byzus 346
     */

    @Execute(required = 2)
    void execute(CommandSender sender, @Arg @Name("target") String target, @Arg @Name("id") int cellId) {
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            sender.sendMessage(Component.text("Nie znaleziono gracza o nicku " + target));
            return;
        }
        UUID uuid = player.getUniqueId();
        CellManager.addPlayer(cellId, sender, uuid);
        sender.sendMessage(Component.text("Pomyślnie dodano gracza do celi."));
    }
}
