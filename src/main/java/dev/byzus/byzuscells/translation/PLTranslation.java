package dev.byzus.byzuscells.translation;

import dev.byzus.byzuscells.component.Components;
import net.kyori.adventure.text.Component;

public class PLTranslation {

    public Component PL_CELL_ALREADY_EXISTS = Components.error("Cela o takim samym numerze już istnieje!");
    public Component PL_CELL_DOESNT_EXIST = Components.error("Taka cela nie istnieje!");
    public Component PL_CELL_DELETED = Components.success("Pomyślnie usunięto celę o numerze: ");
    public Component PL_PLAYER_ADDED_TO_CELL = Components.success("Pomyślnie dodano gracza do celi.");
    public Component PL_CREATED_CELL = Components.success("Pomyślnie utworzono celę o numerze: ");
    public Component PL_PLAYER_REMOVED_FROM_CELL = Components.success("Pomyślnie usunięto gracza z celi.");
    public Component PL_CANNOT_FIND_PLAYER = Components.error("Nie można znaleźć gracza o nicku: ");
    public Component PL_JAILED_PLAYER = Components.success("Gracz został pomyślnie dodany do klatki.");
    public Component PL_PLAYER_HAS_BEEN_UNJAILED = Components.success("Pomyślnie usunięto gracza z klatki.");

    public Component PL_YOU_HAVE_BEEN_UNJAILED = Components.success("Zostałeś usunięty z klatki.");
    public Component PL_YOU_HAVE_BEEN_UNJAILED_BY = Components.success("Zostałeś usunięty z klatki przez: ");

    public Component PL_YOU_HAVE_BEEN_JAILED = Components.error("Zostałeś dodany do klatki!");
    public Component PL_YOU_HAVE_BEEN_JAILED_BY = Components.error("Zostałeś dodany do klatki przez: ");

}
