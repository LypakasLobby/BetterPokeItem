package com.lypaka.betterpokeitem;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static String commandPermission;
    public static List<String> itemStackLore;
    public static List<String> pokemonBlacklist;
    public static boolean preventDexEntry;
    public static Map<String, String> translationMap;

    public static void load() throws ObjectMappingException {

        boolean save = false;
        commandPermission = BetterPokeItem.configManager.getConfigNode(0, "Command-Permission").getString();
        itemStackLore = BetterPokeItem.configManager.getConfigNode(0, "Lore").getList(TypeToken.of(String.class));
        pokemonBlacklist = BetterPokeItem.configManager.getConfigNode(0, "Pokemon-Blacklist").getList(TypeToken.of(String.class));
        preventDexEntry = false;
        if (BetterPokeItem.configManager.getConfigNode(0, "Prevent-Dex-Entry").isVirtual()) {

            BetterPokeItem.configManager.getConfigNode(0, "Prevent-Dex-Entry").setValue(false);
            save = true;

        } else {

            preventDexEntry = BetterPokeItem.configManager.getConfigNode(0, "Prevent-Dex-Entry").getBoolean();

        }
        translationMap = new HashMap<>();
        if (!BetterPokeItem.configManager.getConfigNode(0, "Translations").isVirtual()) {

            translationMap = BetterPokeItem.configManager.getConfigNode(0, "Translations").getValue(new TypeToken<Map<String, String>>() {});

        } else {

            if (!save) save = true;
            translationMap.put("Shiny", "Shiny");
            translationMap.put("Level", "Level");
            translationMap.put("Nature", "Nature");
            translationMap.put("Ability", "Ability");
            translationMap.put("Growth", "Growth");
            translationMap.put("Gender", "Gender");
            translationMap.put("Moves", "Moves");
            translationMap.put("IVs", "IVs");
            translationMap.put("EVs", "EVs");
            translationMap.put("Form", "Form");
            translationMap.put("Palette", "Palette");
            translationMap.put("Nickname", "Nickname");
            translationMap.put("OT", "OT");
            translationMap.put("OTUUID", "OTUUID");
            translationMap.put("Egg", "Egg");
            translationMap.put("Friendship", "Frienship");
            translationMap.put("Health", "Health");
            translationMap.put("Item", "Item");
            translationMap.put("Dynamax Level", "Dynamax Level");
            translationMap.put("Mint Nature", "Mint Nature");
            translationMap.put("GMax", "GMax");
            translationMap.put("EXP", "EXP");
            BetterPokeItem.configManager.getConfigNode(0, "Translations").setValue(translationMap);

        }

        if (save) {

            BetterPokeItem.configManager.save();

        }

    }

}
