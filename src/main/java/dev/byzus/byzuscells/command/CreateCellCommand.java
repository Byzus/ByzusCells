package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

@Route(name = "createcell")
public class CreateCellCommand {

    /*
    /createcell 17 68 43 56
     */

    @Execute(required = 4)
    void execute(Player sender, @Arg double x, @Arg double y, @Arg double z, @Arg @Name("uuid") UUID cellNumber) {
        CellManager.createCell(cellNumber, x, y, z, sender.getWorld());
        sender.sendMessage(Component.text(ChatColor.GREEN + "Pomyslnie utworzono celę o numerze " + cellNumber));
    }

    @Execute(required = 1)
    void executeSelf(Player sender, @Arg @Name("uuid") UUID cellNumber) {
        Location loc = sender.getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        CellManager.createCell(cellNumber, x, y, z, sender.getWorld());
        sender.sendMessage(Component.text(ChatColor.GREEN + "Pomyslnie utworzono celę o numerze " + cellNumber));
    }
}
