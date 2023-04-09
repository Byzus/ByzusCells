package dev.byzus.byzuscells.controller;

import dev.byzus.byzuscells.translation.LanguageManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class PlayerJailController {

    private static final Map<Block, Location> blockData = new HashMap<>();
    private static final Set<UUID> jailedPlayers = new HashSet<>();
    private static final Material MATERIAL = Material.LIGHT_BLUE_STAINED_GLASS;
    private static final Sound SOUND = Sound.BLOCK_ANVIL_LAND;

    public static void jail(CommandSender sender, Player target, int borderSize) {
        Location loc = target.getLocation();
        target.setGameMode(GameMode.ADVENTURE);
        target.playSound(target, SOUND, 1.0F, 1.0F);
        target.sendMessage(LanguageManager.YOU_HAVE_BEEN_JAILED);
        sender.sendMessage(LanguageManager.JAILED_PLAYER);

        for (int x = -borderSize; x <= borderSize; x++) {
            for (int y = -borderSize; y <= borderSize; y++) {
                for (int z = -borderSize; z <= borderSize; z++) {
                    blockData.put(loc.getBlock(), loc);
                    Location blockLoc = loc.clone().add(x, y, z);

                    // set the block at the current location to a barrier block
                    blockLoc.getWorld().getBlockAt(blockLoc).setType(MATERIAL);
                    if (borderSize == 1) {
                        target.getEyeLocation().add(0, 1, 0).getBlock().setType(MATERIAL);
                    }
                    target.getLocation().getBlock().setType(Material.AIR);
                    target.getEyeLocation().getBlock().setType(Material.AIR);
                }
            }
        }
    }


    public static void unJail(CommandSender sender, Player target) {
        final Sound sound = Sound.ENTITY_CAT_AMBIENT;
        target.setGameMode(target.getPreviousGameMode());
        target.playSound(target, sound, 1.0F, 1.0F);
        target.sendMessage(LanguageManager.YOU_HAVE_BEEN_UNJAILED);
        sender.sendMessage(LanguageManager.PLAYER_HAS_BEEN_UNJAILED);
        for (Map.Entry<Block, Location> entry : blockData.entrySet()) {
            Location targetLoc = entry.getValue();
            Block block = entry.getKey();

            targetLoc.getBlock().setType(block.getType());
            targetLoc.getBlock().setBlockData(block.getBlockData());

            CompletableFuture.runAsync(() -> {
                blockData.remove(entry.getKey());
            }).join();
        }
    }
    public static Map<Block, Location> getBlockData() {
        return blockData;
    }

    public static Set<UUID> getJailedPlayers() {
        return jailedPlayers;
    }

}
