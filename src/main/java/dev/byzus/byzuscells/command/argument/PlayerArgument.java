package dev.byzus.byzuscells.command.argument;

import dev.byzus.byzuscells.translation.LanguageManager;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
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
            return Result.error(LanguageManager.CANNOT_FIND_PLAYER);
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
