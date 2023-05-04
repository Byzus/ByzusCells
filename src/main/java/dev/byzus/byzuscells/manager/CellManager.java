package dev.byzus.byzuscells.manager;

import dev.byzus.byzuscells.cell.Cell;
import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.exception.CellAlreadyExistsException;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import panda.std.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CellManager {

    private final Map<Cell, UUID> cells = new HashMap<>();

    public Cell findCell(int id) {
        for (Cell cell : this.getCells().keySet()) {
            if (cell.id() == id) {
                return cell;
            }
        }
        return null;
    }

    public Result<Cell, Exception> createCell(int id, double x, double y, double z, World world) {
        for (Cell cell : this.getCells().keySet()) {
            if (cell.id() == id) {
                return Result.error(new CellAlreadyExistsException("Cell of the same number already exists!"));
            }
        }
        this.getCells().put(new Cell(new Location(world, x, y, z), id), null);
        return Result.ok(new Cell(new Location(world, x, y, z), id));
    }

    public void deleteCell(CommandSender sender, int id) {
        Cell cell = this.findCell(id);
        if (cell == null) {
            sender.sendMessage(Components.error("This cell doesn't exist!"));
            return;
        }
        this.getCells().remove(cell);
        sender.sendMessage(Components.success("Successfully deleted cell of number: ").append(Component.text(id)));
    }

    public void addPlayer(int cellId, CommandSender sender, UUID target) {
        Player player = Bukkit.getPlayer(target);
        Cell cell = this.findCell(cellId);
        if (cell == null) {
            sender.sendMessage(Components.error("This cell doesn't exist!"));
            return;
        }

        if (player == null) {
            sender.sendMessage(Components.error("Cannot find player with name: ").append(Component.text(player.getName())));
        }

        this.getCells().putIfAbsent(cell, target);
        player.teleport(cell.location());
    }

    public void addPlayer(int cellId, CommandSender sender, Player target) {
        this.addPlayer(cellId, sender, target.getUniqueId());
    }

    public void removePlayer(CommandSender sender, UUID target) {
        this.getCells().values().remove(target);
    }

    public Map<Cell, UUID> getCells() {
        return this.cells;
    }

}
