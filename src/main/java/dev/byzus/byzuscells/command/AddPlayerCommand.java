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

@Route(name = "addplayer")
public class AddPlayerCommand {

    /*
    /addplayer Byzus 346
     */

    @Execute(required = 2)
    void execute(Player sender, @Arg @Name("uuid") UUID target, @Arg @Name("uuid") UUID cellNumber ) {
        CellManager.addPlayer(cellNumber, target);
        sender.sendMessage(Component.text(ChatColor.GREEN + "Pomyślnie dodano gracza do celi."));
    }
}
