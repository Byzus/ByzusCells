package dev.byzus.byzuscells.command;

import dev.byzus.byzuscells.manager.GUIManager;
import dev.byzus.byzuscells.manager.PlayerJailManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "unjail")
@Permission("byzuscells.unjail")
public class UnJailPlayerCommand {

    private final PlayerJailManager jailManager;
    private final GUIManager guiManager;

    public UnJailPlayerCommand(PlayerJailManager jailManager, GUIManager guiManager) {
        this.jailManager = jailManager;
        this.guiManager = guiManager;
    }

    @Execute(required = 1)
    void execute(CommandSender sender, @Arg @Name("target") Player target) {
        this.jailManager.unJail(sender, target);
    }

    @Execute(required = 0)
    void executeGui(CommandSender sender) {
        Player target = (Player) sender;
        this.guiManager.showUnjailGUI(target);
    }

}
