package dev.byzus.byzuscells;

import dev.byzus.byzuscells.command.*;
import dev.byzus.byzuscells.command.handler.InvalidUsage;
import dev.byzus.byzuscells.command.handler.PermissionMessage;
import dev.byzus.byzuscells.translation.LanguageManager;
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
        instance = this;
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "byzuscells")
            .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("You must be a player to use this command."))
            .commandInstance(new CreateCellCommand(), new PrisonPlayerCommand(), new DeleteCellCommand(), new JailPlayerCommand(), new UnPrisonPlayerCommand(), new UnJailPlayerCommand())
            .invalidUsageHandler(new InvalidUsage())
            .permissionHandler(new PermissionMessage())
            .register();

        LanguageManager.checkLanguage();
    }

    @Override
    public void onDisable() {
        if (this.liteCommands.getPlatform() != null) {
            this.liteCommands.getPlatform().unregisterAll();
        }
        instance = null;
    }

    public static ByzusCells getInstance() {
        return instance;
    }

}

