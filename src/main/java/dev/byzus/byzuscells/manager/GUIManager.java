package dev.byzus.byzuscells.manager;

/*
 * NOTE: This feature is in beta version and it may be not done properly.
 */

import dev.byzus.byzuscells.component.Components;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIManager {

    private static final int[] slots = {1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43};

    private static void decorateGUI(Inventory inventory) {

        ItemStack frame = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < inventory.getSize(); i++) {
            for (int slot : slots) {
                if (i == slot) {
                    inventory.setItem(i, frame);
                }
            }
        }
    }

    public static void showOnlinePlayersGui(Inventory inventory, Player target) {
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


    public static void jailGUI(Player target) {
        Inventory inventory = Bukkit.createInventory(target, 44, Components.info("Jail GUI"));

        ItemStack exitButton = new ItemStack(Material.BARRIER);
        inventory.setItem(44, exitButton);


        decorateGUI(inventory);
        target.openInventory(inventory);
    }
}
