package dev.byzus.byzuscells.manager;

import dev.byzus.byzuscells.translation.LanguageManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
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

    public static void jail(CommandSender sender, String target, int borderSize) {
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            sender.sendMessage(LanguageManager.CANNOT_FIND_PLAYER.append(Component.text(target)));
            return;
        }
        Location loc = player.getLocation();
        sender.sendMessage(LanguageManager.JAILED_PLAYER);

        if (sender instanceof Player) {
            player.sendMessage(LanguageManager.YOU_HAVE_BEEN_JAILED_BY.append(Component.text(target)));
        } else if (!(sender instanceof Player)) {
            player.sendMessage(LanguageManager.YOU_HAVE_BEEN_JAILED);
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.playSound(player, JAIL_SOUND, 0.25F, 1.0F);

        for (int x = -borderSize; x <= borderSize; x++) {
            for (int y = -borderSize; y <= borderSize; y++) {
                for (int z = -borderSize; z <= borderSize; z++) {
                    Location blockLoc = loc.toBlockLocation().clone().add(x, y, z);
                    jails.put(Triple.of(blockLoc, blockLoc.getBlock().getBlockData(), blockLoc.getBlock().getState()), player.getUniqueId());
                    blockLoc.getBlock().setType(MATERIAL);
                    if (borderSize == 1) {
                        jails.put(Triple.of(player.getEyeLocation().toBlockLocation().add(0, 1, 0), player.getEyeLocation().toBlockLocation().getBlock().getBlockData(), player.getEyeLocation().toBlockLocation().getBlock().getState()), player.getUniqueId());
                        player.getEyeLocation().toBlockLocation().add(0, 1, 0).getBlock().setType(MATERIAL);
                    }
                    player.getLocation().toBlockLocation().getBlock().setType(Material.AIR);
                    player.getEyeLocation().toBlockLocation().getBlock().setType(Material.AIR);
                }
            }
        }
    }

    public static void unJail(CommandSender sender, String target) {
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            sender.sendMessage(LanguageManager.CANNOT_FIND_PLAYER.append(Component.text(target)));
            return;
        }
        sender.sendMessage(LanguageManager.PLAYER_HAS_BEEN_UNJAILED);
        player.sendMessage(LanguageManager.YOU_HAVE_BEEN_UNJAILED);
        player.playSound(player, UNJAIL_SOUND, 0.25F, 0.5F);

        if (player.getPreviousGameMode() == null) {
            player.setGameMode(GameMode.SURVIVAL);
        } else if (!(player.getPreviousGameMode() == null)) {
            player.setGameMode(player.getPreviousGameMode());
        }
        for (Map.Entry<Triple<Location, BlockData, BlockState>, UUID> entry : jails.entrySet()) {
            if (entry.getValue().equals(player.getUniqueId())) {
                entry.getKey().getFirst().getBlock().setBlockData(entry.getKey().getSecond());
                entry.getKey().getFirst().getBlock().getState().update(true);
            }
        }
        jails.clear();
    }
}
