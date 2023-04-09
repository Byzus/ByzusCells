package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.byzus.byzuscells.translation.LanguageManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@Route(name = "unprison", aliases = "removeplayer")
@Permission("byzuscells.unprison")
public class UnPrisonPlayerCommand {

    @Execute(required = 1)
    void execute(CommandSender sender, @Arg @Name("target") String target) {
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            sender.sendMessage(LanguageManager.CannotFindPlayer);
            return;
        }
        UUID uuid = player.getUniqueId();
        player.teleport(PrisonPlayerCommand.playerPreviousLocation);
        CellManager.removePlayer(sender, uuid);
        player.setGameMode(GameMode.SURVIVAL);
        sender.sendMessage(LanguageManager.PlayerAddedToCell);
    }
}
