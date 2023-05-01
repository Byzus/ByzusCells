package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.manager.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Route(name = "prison", aliases = "addplayer")
@Permission("byzuscells.prison")
public class PrisonPlayerCommand {

    public static final Map<UUID, Location> locationData = new HashMap<>();

    private final CellManager cellManager;

    public PrisonPlayerCommand(CellManager cellManager) {
        this.cellManager = cellManager;
    }

    @Execute(required = 2)
    void execute(CommandSender sender, @Arg @Name("target") Player target, @Arg @Name("id") int cellId) {
        if (target == null) {
            sender.sendMessage(Components.error("Cannot find player with name: ").append(Component.text(target.getName())));
            return;
        }
        UUID uuid = target.getUniqueId();
        locationData.put(uuid, target.getLocation());
        this.cellManager.addPlayer(cellId, sender, uuid);
        target.setGameMode(GameMode.ADVENTURE);
        sender.sendMessage(Components.success("Successfully added player to cell."));

    }

}
