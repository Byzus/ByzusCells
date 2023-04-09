package dev.byzus.byzuscells.command.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import panda.utilities.text.Formatter;

import java.util.List;

public class InvalidUsage implements InvalidUsageHandler<CommandSender> {

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        List<String> schematics = schematic.getSchematics();

        if (schematic.isOnlyFirst()) {
            sender.sendMessage(ChatColor.RED + "Invalid usage: " + schematics.get(0));
            return;
        }

        for (String scheme : schematics) {
            Formatter formatter = new Formatter()
                .register("&7› &3{USAGE}", scheme);

            sender.sendMessage(formatter.format("&7› &3{USAGE}"));
        }

    }

}
