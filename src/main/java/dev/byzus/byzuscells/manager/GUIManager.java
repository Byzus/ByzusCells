package dev.byzus.byzuscells.manager;

/*
 * NOTE: This feature is in beta version and it may be not done properly.
 */

import dev.byzus.byzuscells.component.Components;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class GUIManager {

    public void showJailGUI(Player target) {

        GuiItem closeButton = new GuiItem(Material.BARRIER);

        Gui gui = Gui.gui()
            .type(GuiType.CHEST)
            .title(Components.error("Jail GUI"))
            .disableAllInteractions()
            .rows(6)
            .create();

        gui.setItem(53,closeButton);
        gui.addSlotAction(53, event -> gui.close(target));
        List<? extends Player> players = Bukkit.getServer().getOnlinePlayers().stream().toList();

        for (int i = 0; i < players.size(); i++) {
            GuiItem skull = new GuiItem(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) skull.getItemStack().getItemMeta();
            meta.setOwningPlayer(players.get(i));
            meta.setPlayerProfile(players.get(i).getPlayerProfile());
            skull.getItemStack().setItemMeta(meta);
            gui.setItem(i, skull);
        }
        gui.open(target);
    }
}
