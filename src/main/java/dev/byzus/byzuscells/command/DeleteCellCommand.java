package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.component.Components;
import dev.byzus.byzuscells.manager.CellManager;
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
        if (CellManager.findCell(cellId) == null) {
            sender.sendMessage(Components.error("This cell doesn't exist!"));
        }
        CellManager.deleteCell(sender, cellId);
    }
}
