package dev.byzus.byzuscells.translation;

import dev.byzus.byzuscells.ByzusCells;
import net.kyori.adventure.text.Component;

public class LanguageManager {

    static PLTranslation plTranslation = new PLTranslation();
    static ENTranslation enTranslation = new ENTranslation();

    public static Component CellAlreadyExists;
    public static Component CellDoesntExists;
    public static Component CellDeleted;
    public static Component PlayerAddedToCell;
    public static Component CreatedCell;
    public static Component PlayerRemovedFromCell;
    public static Component CannotFindPlayer;
    public static Component JailedPlayer;
    public static Component PlayerHasBeenUnJailed;
    public static Component YouHaveBeenUnjailed;
    public static Component YouHaveBeenJailed;
    public static Component YouMustBeAPlayer;

    public static void setEnglishLanguage() {
        CellAlreadyExists = enTranslation.EN_CellAlreadyExists;
        CellDoesntExists = enTranslation.EN_CellDoesntExists;
        CellDeleted = enTranslation.EN_CellDeleted;
        PlayerAddedToCell = enTranslation.EN_PlayerAddedToCell;
        CreatedCell = enTranslation.EN_CreatedCell;
        PlayerRemovedFromCell = enTranslation.EN_PlayerRemovedFromCell;
        CannotFindPlayer = enTranslation.EN_CannotFindPlayer;
        JailedPlayer = enTranslation.EN_JailedPlayer;
        PlayerHasBeenUnJailed = enTranslation.EN_PlayerHasBeenUnJailed;
        YouHaveBeenUnjailed = enTranslation.EN_YouHaveBeenUnjailed;
        YouHaveBeenJailed = enTranslation.EN_YouHaveBeenJailed;
    }

    public static void setPolishLanguage() {
        CellAlreadyExists = plTranslation.PL_CellAlreadyExists;
        CellDoesntExists = plTranslation.PL_CellDoesntExists;
        CellDeleted = plTranslation.PL_CellDeleted;
        PlayerAddedToCell = plTranslation.PL_PlayerAddedToCell;
        CreatedCell = plTranslation.PL_CreatedCell;
        PlayerRemovedFromCell = plTranslation.PL_PlayerRemovedFromCell;
        CannotFindPlayer = plTranslation.PL_CannotFindPlayer;
        JailedPlayer = plTranslation.PL_JailedPlayer;
        PlayerHasBeenUnJailed = plTranslation.PL_PlayerHasBeenUnJailed;
        YouHaveBeenUnjailed = plTranslation.PL_YouHaveBeenUnjailed;
        YouHaveBeenJailed = plTranslation.PL_YouHaveBeenJailed;
    }

    public static void checkLanguage() {

        String language = ByzusCells.getInstance().getConfig().getString("language");

        if (language.equals("en_en")) {
            setEnglishLanguage();
        } else if (language.equals("pl_pl")) {
            setPolishLanguage();
        } else {
            setPolishLanguage();
        }
        // TODO: Check LanguageCheck and finish translation.
    }

}
