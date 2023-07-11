package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.Cell;
import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.manager.CellManager;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@Route(name = "listcells")
@Permission("byzuscells.listcells")
public class ListCellsCommand {

    private final Server server;
    private final CellManager cellManager;

    public ListCellsCommand(Server server, CellManager cellManager) {
        this.server = server;
        this.cellManager = cellManager;
    }

    @Execute
    void execute(Player sender) {
        sender.sendMessage(Components.info("List of cells: "));
        for (Map.Entry<Cell, UUID> entry : this.cellManager.getCells().entrySet()) {
            Cell cell = entry.getKey();
            sender.sendMessage(Components.info("Cell #" + cell.id() + "[X: " + cell.location().x() + ", Z: " + cell.location().z() + "], Players: " + this.server.getPlayer(entry.getValue())));
        }
    }
}
