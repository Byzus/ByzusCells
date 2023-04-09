package dev.byzus.byzuscells.translation;

import dev.byzus.byzuscells.component.Components;
import net.kyori.adventure.text.Component;

public class ENTranslation {
    public Component EN_CellAlreadyExists = Components.error("Cell of this same number already exists!");
    public Component EN_CellDoesntExists = Components.error("This cell doesn't exist!");
    public Component EN_CellDeleted = Components.success("Successfully deleted cell.");
    public Component EN_PlayerAddedToCell = Components.success("Successfully added player to cell.");
    public Component EN_CreatedCell = Components.success("Successfully created cell.");
    public Component EN_PlayerRemovedFromCell = Components.success("Successfully removed player from cell.");
    public Component EN_CannotFindPlayer = Components.error("This player cannot be found.");
    public Component EN_JailedPlayer = Components.success("Player has been added to jail.");
    public Component EN_PlayerHasBeenUnJailed = Components.success("Player has been successfully unjailed.");
    public Component EN_YouHaveBeenUnjailed = Components.success("You have been unjailed.");
    public Component EN_YouHaveBeenJailed = Components.error("You have been jailed!");
    public Component EN_YouMustBeAPlayer = Components.error("You must be a player to use this command.");
}
