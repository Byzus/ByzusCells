package dev.byzus.byzuscells.translation;

import dev.byzus.byzuscells.component.Components;
import net.kyori.adventure.text.Component;

public class ENTranslation {

    public Component EN_CELL_ALREADY_EXISTS = Components.error("Cell of this same number already exists!");
    public Component EN_CELL_DOESNT_EXIST = Components.error("This cell doesn't exist!");
    public Component EN_CELL_DELETED = Components.success("Successfully deleted cell of number: ");
    public Component EN_PLAYER_ADDED_TO_CELL = Components.success("Successfully added player to cell.");
    public Component EN_CREATED_CELL = Components.success("Successfully created cell of number: ");
    public Component EN_PLAYER_REMOVED_FROM_CELL = Components.success("Successfully removed player from cell.");
    public Component EN_CANNOT_FIND_PLAYER = Components.error("Cannot find player with name: ");
    public Component EN_JAILED_PLAYER = Components.success("Player has been added to jail.");
    public Component EN_PLAYER_HAS_BEEN_UNJAILED = Components.success("Player has been successfully unjailed.");

    public Component EN_YOU_HAVE_BEEN_UNJAILED = Components.success("You have been unjailed");
    public Component EN_YOU_HAVE_BEEN_UNJAILED_BY = Components.success("You have been unjailed by: ");

    public Component EN_YOU_HAVE_BEEN_JAILED = Components.error("You have been jailed");
    public Component EN_YOU_HAVE_BEEN_JAILED_BY = Components.error("You have been jailed by:");

}
