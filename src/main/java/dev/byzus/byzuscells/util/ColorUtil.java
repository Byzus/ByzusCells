package dev.byzus.byzuscells.util;

import org.bukkit.ChatColor;

@Deprecated
public class ColorUtil {
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
