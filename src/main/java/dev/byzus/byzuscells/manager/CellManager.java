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

    private static final Map<Cell, UUID> cells = new HashMap<>();

    public static Cell findCell(int id) {
        for (Cell cell : getCells().keySet()) {
            if (cell.getId() == id) {
                return cell;
            }
        }
        return null;
    }

    public static Result<Cell, Exception> createCell(int id, double x, double y, double z, World world) {
        for (Cell cell : CellManager.getCells().keySet()) {
            if (cell.getId() == id) {
                return Result.error(new CellAlreadyExistsException("Cell of the same number already exists!"));
            }
        }
        CellManager.getCells().put(new Cell(new Location(world, x, y, z), id), null);
        return Result.ok(new Cell(new Location(world, x, y, z), id));
    }

    public static void deleteCell(CommandSender sender, int id) {
        Cell cell = findCell(id);
        if (cell == null) {
            sender.sendMessage(Components.error("This cell doesn't exist!"));
            return;
        }
        CellManager.getCells().remove(cell);
        sender.sendMessage(Components.success("Successfully deleted cell of number: ").append(Component.text(id)));
    }

    public static void addPlayer(int cellId, CommandSender sender, UUID target) {
        Player player = Bukkit.getPlayer(target);
        Cell cell = findCell(cellId);
        if (cell == null) {
            sender.sendMessage(Components.error("This cell doesn't exist!"));
            return;
        }

        if (player == null) {
            sender.sendMessage(Components.error("Cannot find player with name: ").append(Component.text(player.getName())));
        }

        CellManager.getCells().putIfAbsent(cell, target);
        player.teleport(cell.getLocation());
    }

    public static void addPlayer(int cellId, CommandSender sender, Player target) {
        addPlayer(cellId, sender, target.getUniqueId());
    }

    public static void removePlayer(CommandSender sender, UUID target) {
        CellManager.getCells().values().remove(target);
    }

    public static Map<Cell, UUID> getCells() {
        return cells;
    }

}
