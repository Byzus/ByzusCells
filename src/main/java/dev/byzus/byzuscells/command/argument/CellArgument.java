package dev.byzus.byzuscells.command.argument;

import dev.byzus.byzuscells.cell.Cell;
import dev.byzus.byzuscells.manager.CellManager;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import panda.std.Result;

import java.util.List;

public class CellArgument implements OneArgument<Cell> {

    private final CellManager cellManager;

    public CellArgument(CellManager cellManager) {
        this.cellManager = cellManager;
    }

    @Override
    public Result<Cell, ?> parse(LiteInvocation invocation, String argument) {
        return Result.ok(this.cellManager.findCell(Integer.parseInt(argument)));
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return this.cellManager.getCells().keySet()
            .stream()
            .map(Cell::id)
            .map(String::valueOf)
            .map(Suggestion::of)
            .toList();
    }
}
