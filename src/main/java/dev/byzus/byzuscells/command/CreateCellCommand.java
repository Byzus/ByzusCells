package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Route(name = "createcell")
@Permission("byzuscells.createcell")
public class CreateCellCommand {

    /*
    /createcell 17 68 43 56
     */

    @Execute(required = 4)
    void execute(Player sender, @Arg double x, @Arg double y, @Arg double z, @Arg int cellId) {
        CellManager.createCell(cellId, x, y, z, sender.getWorld());
        sender.sendMessage(Component.text("Pomyślnie utworzono celę o numerze " + cellId));
    }

    @Execute(required = 1)
    void executeSelf(Player sender, @Arg @Name("id") int cellId) {
        Location loc = sender.getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        CellManager.createCell(cellId, x, y, z, sender.getWorld());
        sender.sendMessage(Component.text("Pomyslnie utworzono celę o numerze " + cellId));
    }
}
