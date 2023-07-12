package dev.byzus.byzuscells.manager;

import dev.byzus.byzuscells.cell.Cell;
import dev.byzus.byzuscells.exception.CellAlreadyExistsException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import panda.std.Blank;
import panda.std.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CellManager {

    private final Map<Cell, Set<UUID>> cells = new HashMap<>();

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
        Cell cell = new Cell(new Location(world, x, y, z), id);
        this.getCells().put(cell, null);
        return Result.ok(cell);
    }

    public void deleteCell(int id) {
        Cell cell = this.findCell(id);
        if (cell == null) {
            return;
        }
        this.getCells().remove(cell);
    }

    public Result<Blank, Exception> addPlayer(int cellId, UUID target) {
        Player player = Bukkit.getPlayer(target);
        Cell cell = this.findCell(cellId);
        if (cell == null) {
            return Result.error(new NullPointerException("This cell doesn't exist!"));
        }

        if (player == null) {
            return Result.error(new NullPointerException("Cannot find player with this UUID!"));
        }

        this.getCells().putIfAbsent(cell, Set.of(target));
        player.teleport(cell.location());
        return Result.ok();
    }

    public Result<Blank, Exception> addPlayer(int cellId, Player target) {
        return this.addPlayer(cellId, target.getUniqueId());
    }

    public void removePlayer(UUID target) {
        this.getCells().values().remove(target);
    }

    public Map<Cell, Set<UUID>> getCells() {
        return this.cells;
    }

}
