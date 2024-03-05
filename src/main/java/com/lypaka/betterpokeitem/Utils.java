package com.lypaka.betterpokeitem;

import com.lypaka.catalystterapokemon.Helpers.NBTHelpers;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.pixelmonmod.pixelmon.api.pokemon.Nature;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.ability.AbilityRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.species.gender.Gender;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModList;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Utils {

    public static Pokemon rebuildPokemon (String species, List<String> specs) {

        Pokemon pokemon = PokemonBuilder.builder()
                .species(species.replace("pixelmon.", ""))
                .build();

        for (String s : specs) {

            // This is incredibly fucking stupid
            s = s.replace("§0", "").replace("§1", "").replace("§2", "").replace("§3", "")
                    .replace("§4", "").replace("§5", "").replace("§6", "").replace("§7", "")
                    .replace("§8", "").replace("§9", "").replace("§a", "").replace("§b", "")
                    .replace("§c", "").replace("§d", "").replace("§e", "").replace("§f", "")
                    .replace("§k", "").replace("§l", "").replace("§m", "").replace("§n", "")
                    .replace("§o", "").replace("§r", "");
            if (s.contains(", ")) {

                String[] secondarySplit = s.split(": ");
                String pokemonSpec = secondarySplit[0].replace(":", "");
                String[] valueSplit = secondarySplit[1].split(", ");
                pokemonSpec = getTranslatedSpec(pokemonSpec);
                if (pokemonSpec == null) {

                    BetterPokeItem.logger.error("Found a null PokemonSpec from trying to get translated spec from value: " + secondarySplit[0].replace(":", ""));

                }
                switch (pokemonSpec.toLowerCase()) {

                    case "moves":
                        pokemon.getMoveset().clear();
                        for (String move : valueSplit) {

                            if (!move.equalsIgnoreCase("none")) {

                                Attack attack = new Attack(move);
                                pokemon.getMoveset().add(attack);

                            }

                        }
                        break;

                    case "ivs":
                        int[] ivs = new int[6];
                        for (int i = 0; i < valueSplit.length; i++) {

                            ivs[i] = Integer.parseInt(valueSplit[i]);

                        }
                        pokemon.getIVs().fillFromArray(ivs);
                        break;

                    case "evs":
                        int[] evs = new int[6];
                        for (int e = 0; e < valueSplit.length; e++) {

                            evs[e] = Integer.parseInt(valueSplit[e]);

                        }
                        pokemon.getEVs().fillFromArray(evs);
                        break;

                }

            } else {

                try {

                    String[] split = s.split(": ");
                    String pokemonSpec = split[0];
                    String value = split[1];
                    pokemonSpec = getTranslatedSpec(pokemonSpec);
                    if (pokemonSpec == null) {

                        BetterPokeItem.logger.error("Found a null PokemonSpec from trying to get translated spec from value: " + split[0]);

                    }
                    switch (pokemonSpec.toLowerCase()) {

                        case "shiny":
                            pokemon.setShiny(Boolean.parseBoolean(value));
                            break;

                        case "level":
                            pokemon.setLevelNum(Integer.parseInt(value));
                            break;

                        case "nature":
                            pokemon.setNature(Nature.natureFromString(value));
                            break;

                        case "ability":
                            pokemon.setAbility(AbilityRegistry.getAbility(value));
                            break;

                        case "growth":
                            pokemon.setGrowth(EnumGrowth.getGrowthFromString(value));
                            break;

                        case "gender":
                            if (value.equalsIgnoreCase("male")) {

                                pokemon.setGender(Gender.MALE);

                            } else if (value.equalsIgnoreCase("female")) {

                                pokemon.setGender(Gender.FEMALE);

                            } else {

                                pokemon.setGender(Gender.NONE);

                            }
                            break;

                        case "form":
                            pokemon.setForm(value);
                            break;

                        case "palette":
                            if (!value.equalsIgnoreCase("none")) {

                                pokemon.setPalette(value);

                            }
                            break;

                        case "nickname":
                            if (!value.equalsIgnoreCase("none")) {

                                pokemon.setNickname(FancyText.getFormattedText(value));

                            }
                            break;

                        case "friendship":
                            pokemon.setFriendship(Integer.parseInt(value));
                            break;

                        case "egg":
                            if (Boolean.parseBoolean(value)) {

                                pokemon.makeEgg();

                            }
                            break;

                        case "item":
                            if (!value.equalsIgnoreCase("minecraft:air")) {

                                ItemStack item = ItemStackBuilder.buildFromStringID(value);
                                item.setCount(1);
                                pokemon.setHeldItem(item);

                            }
                            break;

                        case "dynamax level":
                            pokemon.setGigantamaxFactor(Boolean.parseBoolean(value));
                            break;

                        case "mint nature":
                            if (!value.equalsIgnoreCase("none")) {

                                pokemon.setMintNature(Nature.natureFromString(value));

                            }
                            break;

                        case "mythic":
                            if (ModList.get().isLoaded("pokemonmythology")) {

                                if (!value.isEmpty()) {

                                    MythicHandler.setMythic(pokemon, MythicHandler.getFromName(value), false);

                                } else if (!value.equalsIgnoreCase("")) {

                                    MythicHandler.setMythic(pokemon, MythicHandler.getFromName(value), false);

                                }

                            }
                            break;

                        case "teratype":
                        case "tera type":
                            if (ModList.get().isLoaded("catalystterapokemon")) {

                                if (!value.isEmpty()) {

                                    NBTHelpers.setTeraType(pokemon, value, false);

                                } else if (!value.equalsIgnoreCase("")) {

                                    NBTHelpers.setTeraType(pokemon, value, false);

                                }

                            }
                            break;

                    }

                } catch (ArrayIndexOutOfBoundsException er) {

                    // do nothing, will error out with nickname if none present

                }

            }

        }

        String ot = null;
        UUID otUUID = null;
        for (String s : specs) {

            if (s.contains("OT: ")) {

                ot = s.replace("OT: ", "");

            }
            if (s.contains("OTUUID: ")) {

                // This is even incredibly stupider
                String value = s.replace("§0", "").replace("§1", "").replace("§2", "").replace("§3", "")
                        .replace("§4", "").replace("§5", "").replace("§6", "").replace("§7", "")
                        .replace("§8", "").replace("§9", "").replace("§a", "").replace("§b", "")
                        .replace("§c", "").replace("§d", "").replace("§e", "").replace("§f", "")
                        .replace("§k", "").replace("§l", "").replace("§m", "").replace("§n", "")
                        .replace("§o", "").replace("§r", "").replace("OTUUID: ", "").replace(" ", "");
                otUUID = UUID.fromString(value);

            }

        }

        if (specs.contains("Health")) {

            for (String s : specs) {

                if (s.contains("Health")) {

                    String[] split1 = s.split(": ");
                    String fullHealth = split1[1];
                    if (fullHealth.contains("/")) {

                        String current = fullHealth.split("/")[0];
                        pokemon.setHealth(Integer.parseInt(current));
                        break;

                    }

                }

            }

        }
        if (specs.contains("EXP")) {

            for (String s : specs) {

                if (s.contains("EXP")) {

                    String[] split1 = s.split(": ");
                    String exp = split1[1];
                    if (exp.contains("/")) {

                        String current = exp.split("/")[0];
                        pokemon.setExperience(Integer.parseInt(current));

                    }

                }

            }

        }

        pokemon.setOriginalTrainer(otUUID, ot);
        return pokemon;

    }

    private static String getTranslatedSpec (String spec) {

        String returnedValue = spec;
        for (Map.Entry<String, String> entry : ConfigGetters.translationMap.entrySet()) {

            if (spec.equalsIgnoreCase(entry.getKey())) returnedValue = entry.getValue();

        }

        return returnedValue;

    }

}
