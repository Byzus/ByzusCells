package dev.byzus.byzuscells.cell;

import org.bukkit.Location;

public class Cell {

    private final Location location;
    private final int id;

    public Cell(Location location, int id) {
        this.location = location;
        this.id = id;
    }

    public Location getLocation() {
        return this.location;
    }

    public int getId() {
        return this.id;
    }
}
