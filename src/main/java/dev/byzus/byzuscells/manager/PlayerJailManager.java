package dev.byzus.byzuscells.manager;

import dev.byzus.byzuscells.component.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import panda.std.Blank;
import panda.std.Result;
import panda.std.Triple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJailManager {

    private static final Material MATERIAL = Material.GRAY_STAINED_GLASS;
    private static final Sound JAIL_SOUND = Sound.BLOCK_ANVIL_LAND;
    private static final Sound UNJAIL_SOUND = Sound.ENTITY_CAT_AMBIENT;
    private final Map<Triple<Location, BlockData, BlockState>, UUID> jails = new HashMap<>();

    public Result<Blank, Exception> jail(@Nullable CommandSender sender, @NotNull Player target, int borderSize) {
        if (target == null) {
            sender.sendMessage(Components.error("Cannot find player with that name!"));
            return Result.error(new NullPointerException("Player is null!"));
        }
        Location loc = target.getLocation();

        if (sender instanceof Player) {
            target.sendMessage(Components.error("You have been jailed by: ").append(Component.text(sender.getName())));
        } else {
            target.sendMessage(Components.error("You have been jailed"));
        }

        target.setGameMode(GameMode.ADVENTURE);
        target.playSound(target, JAIL_SOUND, 0.25F, 1.0F);

        for (int x = -borderSize; x <= borderSize; x++) {
            for (int y = -borderSize; y <= borderSize; y++) {
                for (int z = -borderSize; z <= borderSize; z++) {
                    Location blockLoc = loc.toBlockLocation().clone().add(x, y, z);
                    this.jails.put(Triple.of(blockLoc, blockLoc.getBlock().getBlockData(), blockLoc.getBlock().getState()), target.getUniqueId());
                    blockLoc.getBlock().setType(MATERIAL);
                    if (borderSize == 1) {
                        this.jails.put(Triple.of(target.getEyeLocation().toBlockLocation().add(0, 1, 0), target.getEyeLocation().toBlockLocation().getBlock().getBlockData(), target.getEyeLocation().toBlockLocation().getBlock().getState()), target.getUniqueId());
                        target.getEyeLocation().toBlockLocation().add(0, 1, 0).getBlock().setType(MATERIAL);
                    }
                    target.getLocation().toBlockLocation().getBlock().setType(Material.AIR);
                    target.getEyeLocation().toBlockLocation().getBlock().setType(Material.AIR);
                }
            }
        }
        if (sender != null) {
            sender.sendMessage(Components.success("Player has been jailed."));
        }
        return Result.ok();
    }

    public Result<Blank, Exception> unJail(CommandSender sender, Player target) {
        if (target == null) {
            sender.sendMessage(Components.error("Cannot find player with that name!"));
            return Result.error(new NullPointerException("Player is null!"));
        }

        boolean exists = this.jails.containsValue(target.getUniqueId());

        if (!exists) {
            sender.sendMessage(Components.error("Player is not jailed!"));
            return Result.error(new NullPointerException("Player is not jailed!"));
        }

        target.playSound(target, UNJAIL_SOUND, 0.25F, 0.5F);

        if (target.getPreviousGameMode() == null) {
            target.setGameMode(GameMode.SURVIVAL);
        } else {
            target.setGameMode(target.getPreviousGameMode());
        }

        for (Map.Entry<Triple<Location, BlockData, BlockState>, UUID> entry : this.jails.entrySet()) {
            if (entry.getValue().equals(target.getUniqueId())) {
                entry.getKey().getFirst().getBlock().setBlockData(entry.getKey().getSecond());
                entry.getKey().getFirst().getBlock().getState().update(true);
            }
        }
        this.jails.clear();
        sender.sendMessage(Components.success("Player has been successfully unjailed."));

        if (sender instanceof Player) {
            target.sendMessage(Components.success("You have been unjailed by: ").append(Component.text(sender.getName())));
        } else {
            Components.success("You have been unjailed");
        }
        return Result.ok();
    }

    public Map<Triple<Location, BlockData, BlockState>, UUID> getJails() {
        return this.jails;
    }
}
