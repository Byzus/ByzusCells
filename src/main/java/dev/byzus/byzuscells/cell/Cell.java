package dev.byzus.byzuscells.cell;

import org.bukkit.Location;

import java.util.UUID;

public class Cell {

    private final Location location;
    private final UUID uuid;

    public Cell(Location location, UUID uuid) {
        this.location = location;
        this.uuid = uuid;
    }

    public Location getLocation() {
        return this.location;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
