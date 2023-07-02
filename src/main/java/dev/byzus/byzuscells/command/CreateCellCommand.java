package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.Cell;
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
import panda.std.Result;

@Route(name = "createcell")
@Permission("byzuscells.createcell")
public class CreateCellCommand {

    private final CellManager cellManager;

    public CreateCellCommand(CellManager cellManager) {
        this.cellManager = cellManager;
    }

    @Execute(required = 4)
    void execute(Player sender, @Arg double x, @Arg double y, @Arg double z, @Arg int cellId) {
        this.cellManager.createCell(cellId, x, y, z, sender.getWorld());
        sender.sendMessage(Components.success("Successfully created cell of ID: ").append(Component.text(cellId)));
    }

    @Execute(required = 1)
    void executeSelf(Player sender, @Arg @Name("id") int cellId) {
        Location loc = sender.getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        if (this.cellManager.findCell(cellId) != null) {
            sender.sendMessage(Components.error("Cell of this same ID already exists!"));
            return;
        }
        Result<Cell, Exception> result = this.cellManager.createCell(cellId, x, y, z, sender.getWorld());

        if (result.isOk()) {
            sender.sendMessage(Components.success("Successfully created cell of ID: ").append(Component.text(cellId)));
        } else {
            sender.sendMessage(Components.error("Error while creating cell!"));
        }


    }

}
