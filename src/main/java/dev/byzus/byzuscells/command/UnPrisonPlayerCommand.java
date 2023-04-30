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

import java.util.UUID;

@Route(name = "unprison", aliases = "removeplayer")
@Permission("byzuscells.unprison")
public class UnPrisonPlayerCommand {

    private final CellManager cellManager;

    public UnPrisonPlayerCommand(CellManager cellManager) {
        this.cellManager = cellManager;
    }

    @Execute(required = 1)
    void execute(CommandSender sender, @Arg @Name("target") Player target) {
        if (target == null) {
            sender.sendMessage(Components.error("Cannot find player with name: ").append(Component.text(target.getName())));
            return;
        }
        UUID uuid = target.getUniqueId();
        Location targetLoc = PrisonPlayerCommand.locationData.get(target.getUniqueId());
        target.teleport(targetLoc.toBlockLocation());
        this.cellManager.removePlayer(sender, uuid);

        if (target.getPreviousGameMode() == null) {
            target.setGameMode(GameMode.SURVIVAL);
        } else if (!(target.getPreviousGameMode() == null)) {
            target.setGameMode(target.getPreviousGameMode());
        }
        sender.sendMessage(Components.success("Successfully removed player from cell."));
    }

}
