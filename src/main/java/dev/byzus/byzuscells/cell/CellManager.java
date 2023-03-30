package dev.byzus.byzuscells.cell;

import dev.byzus.byzuscells.exception.CellAlreadyExistsException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import panda.std.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CellManager {

    private static final Map<Cell, UUID> cells = new HashMap<>();

    public static Cell findCell(@NotNull UUID uuid) {
        for (Cell cell : getCells().keySet()) {
            if (cell.getUUID().equals(uuid)) {
                return cell;
            }
        }
        return null;
    }

    public static Result<Cell, CellAlreadyExistsException> createCell(UUID uuid, double x, double y, double z, World world) {
        for (Cell cell : CellManager.getCells().keySet()) {
            if (cell.getUUID().equals(uuid)) {
                return Result.error(new CellAlreadyExistsException("Cela o tym samym numerze już istnienieje!"));
            }
        }
        return Result.ok(new Cell(new Location(world, x, y, z), uuid));
    }

    public static void deleteCell(UUID uuid) {
        Cell cell = findCell(uuid);
        CellManager.getCells().remove(cell);
    }

    public static void addPlayer(UUID cellUUID, UUID target) {
        Player player = Bukkit.getPlayer(target);
        Cell cell = findCell(cellUUID);
        CellManager.getCells().put(cell, target);
        player.teleport(cell.getLocation());
    }

    public static void removePlayer(UUID target) {
        CellManager.getCells().values().remove(target);
    }

    public static Map<Cell, UUID> getCells() {
        return cells;
    }
}
