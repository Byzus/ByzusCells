package dev.byzus.byzuscells;

import dev.byzus.byzuscells.command.AddPlayerCommand;
import dev.byzus.byzuscells.command.CreateCellCommand;
import dev.byzus.byzuscells.command.DeleteCellCommand;
import dev.byzus.byzuscells.command.RemovePlayerCommand;
import dev.byzus.byzuscells.command.handler.InvalidUsage;
import dev.byzus.byzuscells.command.handler.PermissionMessage;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ByzusCells extends JavaPlugin {

    private static ByzusCells instance;

    private LiteCommands<CommandSender> liteCommands;

    @Override
    public void onEnable() {
        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "byzuscells")
            .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("You must be a player to use this command."))
            .commandInstance(new CreateCellCommand())
            .commandInstance(new AddPlayerCommand())
            .commandInstance(new DeleteCellCommand())
            .commandInstance(new RemovePlayerCommand())
            .invalidUsageHandler(new InvalidUsage())
            .permissionHandler(new PermissionMessage())
            .register();
    }

    @Override
    public void onDisable() {
        if (this.liteCommands.getPlatform() != null) {
            this.liteCommands.getPlatform().unregisterAll();
        }
    }

    public static ByzusCells getInstance() {
        return instance;
    }

}
