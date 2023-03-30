package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.cell.CellManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.command.CommandSender;

@Route(name = "deletecell")
@Permission("byzuscells.deletecell")
public class DeleteCellCommand {

    @Execute(required = 1)
    void executeSelf(CommandSender sender, @Arg @Name("id") int cellId) {
        CellManager.deleteCell(sender, cellId);
    }
}
