package dev.byzus.byzuscells.translation;

import dev.byzus.byzuscells.component.Components;
import net.kyori.adventure.text.Component;

public class PLTranslation {
    public Component PL_CellAlreadyExists = Components.error("Cela o takim samym numerze już istnieje!");
    public Component PL_CellDoesntExists = Components.error("Taka cela nie istnieje!");
    public Component PL_CellDeleted = Components.success("Pomyślnie usunięto celę");
    public Component PL_PlayerAddedToCell = Components.success("Pomyślnie dodano gracza do celi.");
    public Component PL_CreatedCell = Components.success("Pomyślnie utworzono celę.");
    public Component PL_PlayerRemovedFromCell = Components.success("Pomyślnie usunięto gracza z celi.");
    public Component PL_CannotFindPlayer = Components.error("Nie można znaleźć gracza o nicku ");
    public Component PL_JailedPlayer = Components.success("Gracz został pomyślnie dodany do klatki.");
    public Component PL_PlayerHasBeenUnJailed = Components.success("Pomyślnie usunięto gracza z klatki.");
    public Component PL_YouHaveBeenUnjailed = Components.success("Zostałeś usunięty z klatki.");
    public Component PL_YouHaveBeenJailed = Components.error("Zostałeś dodany do klatki!");
}
