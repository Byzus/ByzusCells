package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.manager.CellManager;
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

    @Execute(required = 4)
    void execute(Player sender, @Arg double x, @Arg double y, @Arg double z, @Arg int cellId) {
        CellManager.createCell(cellId, x, y, z, sender.getWorld());
        sender.sendMessage(Components.success("Successfully created cell of number: ").append(Component.text(cellId)));
    }

    @Execute(required = 1)
    void executeSelf(Player sender, @Arg @Name("id") int cellId) {
        Location loc = sender.getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        if (CellManager.findCell(cellId) != null) {
            sender.sendMessage(Components.error("Cell of this same number already exists!"));
        }
        CellManager.createCell(cellId, x, y, z, sender.getWorld());
        sender.sendMessage(Components.success("Successfully created cell of number: ").append(Component.text(cellId)));

    }

}
