package dev.byzus.byzuscells.component;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class Components {

    private Components() {
        throw new UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    public static Component error(String text) {
        return Component.text(text).color(TextColor.color(255, 0, 0));
    }

    public static Component success(String text) {
        return Component.text(text).color(TextColor.color(0, 255, 0));
    }

    public static Component info(String text) {
        return Component.text(text).color(TextColor.color(0, 170, 170));
    }

    public static Component warning(String text) {
        return Component.text(text).color(TextColor.color(255, 170, 0));
    }

    public static Component fatal(String text) {
        return Component.text(text).color(TextColor.color(210, 0, 0));
    }

    public static Component custom(String text, int r, int g, int b) {
        return Component.text(text).color(TextColor.color(r, g, b));
    }

}
