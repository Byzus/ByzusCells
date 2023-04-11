package dev.byzus.byzuscells.controller;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import panda.std.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJailController {

    private static final Map<Pair<Location, Material>, UUID> jails = new HashMap<>();
    private static final Material MATERIAL = Material.LIGHT_BLUE_STAINED_GLASS;
    private static final Sound SOUND = Sound.BLOCK_ANVIL_LAND;


    public static void jail(CommandSender sender, Player target, int borderSize) {
        Location loc = target.getLocation();
        target.setGameMode(GameMode.ADVENTURE);
        target.playSound(target, SOUND, 0.25F, 0.5F);

        for (int x = -borderSize; x <= borderSize; x++) {
            for (int y = -borderSize; y <= borderSize; y++) {
                for (int z = -borderSize; z <= borderSize; z++) {
                    Location blockLoc = loc.toBlockLocation().clone().add(x, y, z);
                    jails.put(Pair.of(blockLoc, blockLoc.getBlock().getType()), target.getUniqueId()); // save old block
                    blockLoc.getBlock().setType(MATERIAL);
                    if (borderSize == 1) {
                        jails.put(Pair.of(target.getEyeLocation().toBlockLocation().add(0, 1, 0), target.getEyeLocation().toBlockLocation().getBlock().getBlockData().getMaterial()), target.getUniqueId());
                        target.getEyeLocation().toBlockLocation().add(0, 1, 0).getBlock().setType(MATERIAL);
                    }
                    target.getLocation().toBlockLocation().getBlock().setType(Material.AIR);
                    target.getEyeLocation().toBlockLocation().getBlock().setType(Material.AIR);
                }
            }
        }
    }

    public static void unjail(Player target) {
        for (Map.Entry<Pair<Location, Material>, UUID> entry : jails.entrySet()) {
            if (entry.getValue().equals(target.getUniqueId())) {
                entry.getKey().getFirst().getBlock().setType(entry.getKey().getSecond());
            }
        }
        jails.clear();
    }

}

