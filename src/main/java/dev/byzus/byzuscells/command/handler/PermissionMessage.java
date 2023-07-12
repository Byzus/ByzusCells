package dev.byzus.byzuscells.command.handler;

import dev.byzus.byzuscells.component.Components;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.command.permission.RequiredPermissions;
import dev.rollczi.litecommands.handle.PermissionHandler;
import org.bukkit.command.CommandSender;
import panda.utilities.text.Formatter;
import panda.utilities.text.Joiner;

public class PermissionMessage implements PermissionHandler<CommandSender> {

    @Override
    public void handle(CommandSender commandSender, LiteInvocation invocation, RequiredPermissions requiredPermissions) {
        String value = Joiner.on(", ")
            .join(requiredPermissions.getPermissions())
            .toString();

        Formatter formatter = new Formatter()
            .register("{PERMISSION}", value);

        commandSender.sendMessage(Components.error("You don't have permission to use this command: " + formatter.format("{PERMISSION}")));
    }

}
