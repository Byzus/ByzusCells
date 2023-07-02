package dev.byzus.byzuscells.command.handler;

import dev.byzus.byzuscells.component.Components;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import org.bukkit.command.CommandSender;
import panda.utilities.text.Formatter;

import java.util.List;

public class InvalidUsage implements InvalidUsageHandler<CommandSender> {

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        List<String> schematics = schematic.getSchematics();

        if (schematic.isOnlyFirst()) {
            sender.sendMessage(Components.error("Invalid usage: " + schematics.get(0)));
            return;
        }

        for (String scheme : schematics) {
            Formatter formatter = new Formatter()
                .register("{USAGE}", scheme);

            sender.sendMessage(Components.custom("» ", 70, 70, 70).append(Components.error(formatter.format("{USAGE}"))));
        }

    }

}
