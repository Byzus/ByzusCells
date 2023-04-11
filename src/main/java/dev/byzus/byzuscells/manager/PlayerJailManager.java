package dev.byzus.byzuscells.manager;

import dev.byzus.byzuscells.translation.LanguageManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import panda.std.Triple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJailManager {

    private static final Map<Triple<Location, BlockData, BlockState>, UUID> jails = new HashMap<>();
    private static final Material MATERIAL = Material.GRAY_STAINED_GLASS;
    private static final Sound JAIL_SOUND = Sound.BLOCK_ANVIL_LAND;
    private static final Sound UNJAIL_SOUND = Sound.ENTITY_CAT_AMBIENT;

    public static void jail(CommandSender sender, Player target, int borderSize) {
        Location loc = target.getLocation();
        sender.sendMessage(LanguageManager.JAILED_PLAYER);
        target.sendMessage(LanguageManager.YOU_HAVE_BEEN_JAILED);
        target.setGameMode(GameMode.ADVENTURE);
        target.playSound(target, JAIL_SOUND, 0.25F, 0.5F);

        for (int x = -borderSize; x <= borderSize; x++) {
            for (int y = -borderSize; y <= borderSize; y++) {
                for (int z = -borderSize; z <= borderSize; z++) {
                    Location blockLoc = loc.toBlockLocation().clone().add(x, y, z);
                    jails.put(Triple.of(blockLoc, blockLoc.getBlock().getBlockData(), blockLoc.getBlock().getState()), target.getUniqueId()); // save old block
                    blockLoc.getBlock().setType(MATERIAL);
                    if (borderSize == 1) {
                        jails.put(Triple.of(target.getEyeLocation().toBlockLocation().add(0, 1, 0), target.getEyeLocation().toBlockLocation().getBlock().getBlockData(), target.getEyeLocation().toBlockLocation().getBlock().getState()), target.getUniqueId());
                        target.getEyeLocation().toBlockLocation().add(0, 1, 0).getBlock().setType(MATERIAL);
                    }
                    target.getLocation().toBlockLocation().getBlock().setType(Material.AIR);
                    target.getEyeLocation().toBlockLocation().getBlock().setType(Material.AIR);
                }
            }
        }
    }

    public static void unJail(CommandSender sender, Player target) {
        sender.sendMessage(LanguageManager.PLAYER_HAS_BEEN_UNJAILED);
        target.sendMessage(LanguageManager.YOU_HAVE_BEEN_UNJAILED);
        target.playSound(target, UNJAIL_SOUND, 0.25F, 0.5F);

        if (target.getPreviousGameMode() == null) {
            target.setGameMode(GameMode.SURVIVAL);
        }
        target.setGameMode(target.getPreviousGameMode());

        for (Map.Entry<Triple<Location, BlockData, BlockState>, UUID> entry : jails.entrySet()) {
            if (entry.getValue().equals(target.getUniqueId())) {
                entry.getKey().getFirst().getBlock().setBlockData(entry.getKey().getSecond());
                entry.getKey().getFirst().getBlock().getState().update(true);
            }
        }
        jails.clear();
    }
}
