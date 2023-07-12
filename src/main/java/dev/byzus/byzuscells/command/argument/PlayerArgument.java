package dev.byzus.byzuscells.command.argument;

import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import panda.std.Result;

import java.util.List;

@ArgumentName("player")
public class PlayerArgument implements OneArgument<Player>  {

    private final Server server;

    public PlayerArgument(Server server) {
        this.server = server;
    }

    @Override
    public Result<Player, ?> parse(LiteInvocation invocation, String argument) {
        Player player = this.server.getPlayer(argument);

        if (player == null) {
            invocation.sender().sendMessage(ChatColor.RED + "Cannot find player with this name.");
            return Result.error();
        }

        return Result.ok(player);
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return this.server.getOnlinePlayers().stream()
            .map(HumanEntity::getName)
            .map(Suggestion::of)
            .toList();
    }
}
