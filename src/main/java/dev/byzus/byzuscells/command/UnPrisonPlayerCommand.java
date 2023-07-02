package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.manager.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@Route(name = "unprison")
@Permission("byzuscells.unprison")
public class UnPrisonPlayerCommand {

    private final CellManager cellManager;

    public UnPrisonPlayerCommand(CellManager cellManager) {
        this.cellManager = cellManager;
    }

    @Execute(required = 1)
    void execute(CommandSender sender, @Arg @Name("target") Player target) {
        if (target == null) {
            sender.sendMessage(Components.error("Cannot find player with given name!: "));
            return;
        }
        UUID uuid = target.getUniqueId();
        Location targetLoc = PrisonPlayerCommand.locationData.get(target.getUniqueId());
        if (targetLoc == null) {
            sender.sendMessage(Components.error("This player is not in cell!"));
            return;
        }
        target.teleport(targetLoc.toBlockLocation());
        this.cellManager.removePlayer(uuid);

        if (target.getPreviousGameMode() == null) {
            target.setGameMode(GameMode.SURVIVAL);
        } else {
            target.setGameMode(target.getPreviousGameMode());
        }
        sender.sendMessage(Components.success("Successfully removed player from cell."));
    }

}
