package dev.byzus.byzuscells.translation;

import dev.byzus.byzuscells.ByzusCells;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;

public class LanguageManager {

    static PLTranslation plTranslation = new PLTranslation();
    static ENTranslation enTranslation = new ENTranslation();

    public static Component CELL_ALREADY_EXISTS;
    public static Component CELL_DOESNT_EXIST;
    public static Component CELL_DELETED;
    public static Component PLAYER_ADDED_TO_CELL;
    public static Component CREATED_CELL;
    public static Component PLAYER_REMOVED_FROM_CELL;
    public static Component CANNOT_FIND_PLAYER;
    public static Component JAILED_PLAYER;
    public static Component PLAYER_HAS_BEEN_UNJAILED;
    public static Component YOU_HAVE_BEEN_UNJAILED;
    public static Component YOU_HAVE_BEEN_JAILED;

    public static void setEnglishLanguage() {
        CELL_ALREADY_EXISTS = enTranslation.EN_CELL_ALREADY_EXISTS;
        CELL_DOESNT_EXIST = enTranslation.EN_CELL_DOESNT_EXIST;
        CELL_DELETED = enTranslation.EN_CELL_DELETED;
        PLAYER_ADDED_TO_CELL = enTranslation.EN_PLAYER_ADDED_TO_CELL;
        CREATED_CELL = enTranslation.EN_CREATED_CELL;
        PLAYER_REMOVED_FROM_CELL = enTranslation.EN_PLAYER_REMOVED_FROM_CELL;
        CANNOT_FIND_PLAYER = enTranslation.EN_CANNOT_FIND_PLAYER;
        JAILED_PLAYER = enTranslation.EN_JAILED_PLAYER;
        PLAYER_HAS_BEEN_UNJAILED = enTranslation.EN_PLAYER_HAS_BEEN_UNJAILED;
        YOU_HAVE_BEEN_UNJAILED = enTranslation.EN_YOU_HAVE_BEEN_UNJAILED;
        YOU_HAVE_BEEN_JAILED = enTranslation.EN_YOU_HAVE_BEEN_JAILED;
    }

    public static void setPolishLanguage() {
        CELL_ALREADY_EXISTS = plTranslation.PL_CELL_ALREADY_EXISTS;
        CELL_DOESNT_EXIST = plTranslation.PL_CELL_DOESNT_EXIST;
        CELL_DELETED = plTranslation.PL_CELL_DELETED;
        PLAYER_ADDED_TO_CELL = plTranslation.PL_PLAYER_ADDED_TO_CELL;
        CREATED_CELL = plTranslation.PL_CREATED_CELL;
        PLAYER_REMOVED_FROM_CELL = plTranslation.PL_PLAYER_REMOVED_FROM_CELL;
        CANNOT_FIND_PLAYER = plTranslation.PL_CANNOT_FIND_PLAYER;
        JAILED_PLAYER = plTranslation.PL_JAILED_PLAYER;
        PLAYER_HAS_BEEN_UNJAILED = plTranslation.PL_PLAYER_HAS_BEEN_UNJAILED;
        YOU_HAVE_BEEN_UNJAILED = plTranslation.PL_YOU_HAVE_BEEN_UNJAILED;
        YOU_HAVE_BEEN_JAILED = plTranslation.PL_YOU_HAVE_BEEN_JAILED;
    }

    public static void checkLanguage() {

        String language = ByzusCells.getInstance().getConfig().getString("language");

        if (StringUtils.containsIgnoreCase(language, "en_en")) {
            setEnglishLanguage();
        } else if (StringUtils.containsIgnoreCase(language, "pl_pl")) {
            setPolishLanguage();
        } else {
            setEnglishLanguage();

        }

    }

}
