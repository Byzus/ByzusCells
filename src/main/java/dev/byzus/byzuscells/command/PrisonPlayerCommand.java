package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.manager.CellManager;
import dev.byzus.byzuscells.translation.LanguageManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@Route(name = "prison", aliases = "addplayer")
@Permission("byzuscells.prison")
public class PrisonPlayerCommand {

    public static Location playerPreviousLocation;

    @Execute(required = 2)
    void execute(CommandSender sender, @Arg @Name("target") String target, @Arg @Name("id") int cellId) {
        Player player = Bukkit.getPlayer(target);
        if (player == null) {
            sender.sendMessage(LanguageManager.CANNOT_FIND_PLAYER + target);
            return;
        }
        playerPreviousLocation = player.getLocation();
        UUID uuid = player.getUniqueId();
        CellManager.addPlayer(cellId, sender, uuid);
        player.setGameMode(GameMode.ADVENTURE);
        sender.sendMessage(LanguageManager.PLAYER_ADDED_TO_CELL);
    }

}
