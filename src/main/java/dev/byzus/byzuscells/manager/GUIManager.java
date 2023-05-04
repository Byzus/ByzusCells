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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIManager {

    @Deprecated
    public void showOnlinePlayersGui(Inventory inventory, Player target) {
        for (int i = 0; i < inventory.getSize(); i++) {
            int finalI = i;
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwningPlayer(player);
                    meta.setPlayerProfile(player.getPlayerProfile());
                    skull.setItemMeta(meta);
                    inventory.setItem(finalI, skull);
                });
            target.openInventory(inventory);
        }
    }

    public void jailGUI(Player target) {

        GuiItem closeButton = new GuiItem(Material.BARRIER);
        int slots = 54;

        Gui gui = Gui.gui()
            .type(GuiType.CHEST)
            .title(Components.fatal("Jail GUI"))
            .disableAllInteractions()
            .rows(6)
            .create();

        gui.setItem(54,closeButton);
        gui.addSlotAction(54, event -> gui.close(target));

        for (int i = 0; i < slots; i++) {
            int finalI = i;
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                GuiItem skull = new GuiItem(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) skull.getItemStack().getItemMeta();
                meta.setOwningPlayer(player);
                meta.setPlayerProfile(player.getPlayerProfile());
                skull.getItemStack().setItemMeta(meta);
                gui.setItem(finalI, skull);
            });

        }
        gui.open(target);
    }
}
