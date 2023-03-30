package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;

import java.util.UUID;

@Route(name = "deletecell")
public class DeleteCellCommand {

    /*
    /deletecell 76
     */

    @Execute(required = 1)
    void executeSelf(@Arg @Name("uuid") UUID cellNumber) {
        CellManager.deleteCell(cellNumber);
    }
}
