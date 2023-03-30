package dev.byzus.byzuscells.command.argument;

import dev.byzus.byzuscells.cell.Cell;
import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import panda.std.Result;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@ArgumentName("uuid")
public class UUIDArgument implements OneArgument<UUID> {

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return CellManager.getCells().keySet().stream()
                .map(Cell::getUUID)
                .map(UUID::toString)
                .map(Suggestion::of)
                .toList();
    }

    @Override
    public Result<UUID, ?> parse(LiteInvocation invocation, String argument) {
        if (argument.isBlank() || argument.isEmpty()) {
            return Result.error("Couldn't find cell of provided UUID.");
        }
        return Result.ok(UUID.fromString(argument.toLowerCase(Locale.ROOT)));
    }
}
