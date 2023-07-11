package dev.byzus.byzuscells.manager;

import dev.byzus.byzuscells.component.Components;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class GUIManager {

    private final PlayerJailManager jailManager;
    private final CellManager cellManager;
    private final Server server;

    public GUIManager(PlayerJailManager jailManager, CellManager cellManager, Server server) {
        this.jailManager = jailManager;
        this.cellManager = cellManager;
        this.server = server;
    }

    public void showJailGUI(Player target) {

        Gui gui = Gui.gui()
            .title(Components.error("Jail GUI"))
            .disableAllInteractions()
            .rows(6)
            .create();

        GuiItem closeButton = new GuiItem(Material.BARRIER, event -> gui.close(target));
        gui.setItem(53, closeButton);
        List<? extends Player> players = Bukkit.getServer().getOnlinePlayers().stream().toList();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (!this.jailManager.getJails().containsValue(player.getUniqueId())) {
                GuiItem skull = new GuiItem(Material.PLAYER_HEAD, event -> {
                    this.jailManager.jail(target, player, 2);
                    gui.close(target);
                });
                SkullMeta meta = (SkullMeta) skull.getItemStack().getItemMeta();
                meta.setOwningPlayer(player);
                meta.setPlayerProfile(player.getPlayerProfile());
                skull.getItemStack().setItemMeta(meta);
                gui.setItem(i, skull);
            }
        }
        gui.open(target);
    }

    public void showUnjailGUI(Player target) {

        Gui gui = Gui.gui()
            .title(Components.error("Unjail GUI"))
            .disableAllInteractions()
            .rows(6)
            .create();

        GuiItem closeButton = new GuiItem(Material.BARRIER, event -> gui.close(target));
        gui.setItem(53, closeButton);

        this.jailManager.getJails().values()
            .stream()
            .distinct()
            .map(this.server::getPlayer)
            .forEach(player -> {
                GuiItem skull = new GuiItem(Material.PLAYER_HEAD, event -> {
                    this.jailManager.unJail(target, player);
                    gui.close(target);
                });

                SkullMeta meta = (SkullMeta) skull.getItemStack().getItemMeta();
                meta.setOwningPlayer(player);
                meta.setPlayerProfile(player.getPlayerProfile());
                skull.getItemStack().setItemMeta(meta);
                gui.addItem(skull);
        });

        gui.open(target);
    }

    public void showUnPrisonGUI(Player target) {


        Gui gui = Gui.gui()
            .title(Components.error("UnPrison GUI"))
            .disableAllInteractions()
            .rows(6)
            .create();

        GuiItem closeButton = new GuiItem(Material.BARRIER, event -> gui.close(target));
        gui.setItem(53, closeButton);
        List<? extends Player> players = Bukkit.getServer().getOnlinePlayers().stream().toList();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            GuiItem skull = new GuiItem(Material.PLAYER_HEAD, event -> {
                this.cellManager.removePlayer(player.getUniqueId());
                gui.close(target);
            });
            SkullMeta meta = (SkullMeta) skull.getItemStack().getItemMeta();
            meta.setOwningPlayer(player);
            meta.setPlayerProfile(player.getPlayerProfile());
            skull.getItemStack().setItemMeta(meta);
            gui.setItem(i, skull);
        }
        gui.open(target);
    }
}
