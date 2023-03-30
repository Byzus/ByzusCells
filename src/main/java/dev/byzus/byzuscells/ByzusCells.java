package dev.byzus.byzuscells;

import dev.byzus.byzuscells.command.AddPlayerCommand;
import dev.byzus.byzuscells.command.CreateCellCommand;
import dev.byzus.byzuscells.command.DeleteCellCommand;
import dev.byzus.byzuscells.command.RemovePlayerCommand;
import dev.byzus.byzuscells.command.argument.UUIDArgument;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class ByzusCells extends JavaPlugin {

    private LiteCommands<CommandSender> liteCommands;


    @Override
    public void onEnable() {
        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "byzuscells")
                .argument(UUID.class, new UUIDArgument())
                .commandInstance(new CreateCellCommand())
                .commandInstance(new AddPlayerCommand())
                .commandInstance(new DeleteCellCommand())
                .commandInstance(new RemovePlayerCommand())
                .register();

         /*
        Stream.of(
                this,
                new PlayerEggThrowListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
        */

    }

    @Override
    public void onDisable() {
        if (this.liteCommands.getPlatform() != null) {
            this.liteCommands.getPlatform().unregisterAll();
        }
    }
}
