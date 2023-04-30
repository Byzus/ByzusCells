package dev.byzus.byzuscells;

import com.google.common.base.Stopwatch;
import dev.byzus.byzuscells.command.CreateCellCommand;
import dev.byzus.byzuscells.command.DeleteCellCommand;
import dev.byzus.byzuscells.command.JailPlayerCommand;
import dev.byzus.byzuscells.command.PrisonPlayerCommand;
import dev.byzus.byzuscells.command.UnJailPlayerCommand;
import dev.byzus.byzuscells.command.UnPrisonPlayerCommand;
import dev.byzus.byzuscells.command.argument.PlayerArgument;
import dev.byzus.byzuscells.command.handler.InvalidUsage;
import dev.byzus.byzuscells.command.handler.PermissionMessage;
import dev.byzus.byzuscells.manager.CellManager;
import dev.byzus.byzuscells.manager.GUIManager;
import dev.byzus.byzuscells.manager.PlayerJailManager;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class ByzusCells extends JavaPlugin {

    private static ByzusCells instance;

    private LiteCommands<CommandSender> liteCommands;


    @Override
    public void onEnable() {
        Stopwatch started = Stopwatch.createStarted();
        instance = this;
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        CellManager cellManager = new CellManager();
        GUIManager guiManager = new GUIManager();
        PlayerJailManager jailManager = new PlayerJailManager();

        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "byzuscells")
            .argument(Player.class, new PlayerArgument(this.getServer()))
            .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("You must be a player to use this command."))
            .commandInstance(new CreateCellCommand(cellManager),
                new PrisonPlayerCommand(),
                new DeleteCellCommand(cellManager),
                new JailPlayerCommand(guiManager, jailManager),
                new UnPrisonPlayerCommand(cellManager),
                new UnJailPlayerCommand(jailManager))
            .invalidUsageHandler(new InvalidUsage())
            .permissionHandler(new PermissionMessage())
            .register();

        long millis = started.elapsed(TimeUnit.MILLISECONDS);
        this.getLogger().info("Successfully enabled ByzusCells in " + millis + "ms");
    }
    

    @Override
    public void onDisable() {

    }

    public static ByzusCells getInstance() {
        return instance;
    }

}

