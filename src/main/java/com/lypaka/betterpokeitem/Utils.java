package com.lypaka.betterpokeitem;

import com.lypaka.catalystterapokemon.Helpers.NBTHelpers;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.ItemStackBuilder;
import com.lypaka.pokemonmythology.Handlers.MythicHandler;
import com.pixelmonmod.pixelmon.api.pokemon.Nature;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.pokemon.ability.AbilityRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.species.gender.Gender;
import com.pixelmonmod.pixelmon.api.pokemon.stats.extraStats.LakeTrioStats;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.ModList;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Utils {

    private static Pokemon rebuildPokemonWithStupidAssName (String species) {

        species = species.replace("pixelmon.", "");
        Pokemon pokemon = null;
        switch (species.toLowerCase()) {

            case "nidoranfemale":
            case "nidoran♀":
                pokemon = PokemonBuilder.builder()
                        .species(29)
                        .build();
                break;

            case "nidoranmale":
            case "nidoran♂":
                pokemon = PokemonBuilder.builder()
                        .species(32)
                        .build();
                break;

            case "farfetch'd":
            case "farfetchd":
                pokemon = PokemonBuilder.builder()
                        .species(83)
                        .build();
                break;

            case "mrmime":
            case "mr.mime":
            case "mr. mime":
            case "mr mime":
                pokemon = PokemonBuilder.builder()
                        .species(122)
                        .build();
                break;

            case "porygon2":
                pokemon = PokemonBuilder.builder()
                        .species(233)
                        .build();
                break;

            case "mimejr.":
            case "mimejr":
            case "mime jr.":
            case "mime jr":
                pokemon = PokemonBuilder.builder()
                        .species(439)
                        .build();
                break;

            case "porygon-z":
            case "porygon z":
            case "porygonz":
                pokemon = PokemonBuilder.builder()
                        .species(474)
                        .build();
                break;

            case "flabébé":
            case "flabebe":
                pokemon = PokemonBuilder.builder()
                        .species(669)
                        .build();
                break;

            case "typenull":
            case "type null":
            case "type:null":
            case "type: null":
                pokemon = PokemonBuilder.builder()
                        .species(772)
                        .build();
                break;

            case "tapu koko":
            case "tapukoko":
                pokemon = PokemonBuilder.builder()
                        .species(785)
                        .build();
                break;

            case "tapu lele":
            case "tapulele":
                pokemon = PokemonBuilder.builder()
                        .species(786)
                        .build();
                break;

            case "tapu bulu":
            case "tapubulu":
                pokemon = PokemonBuilder.builder()
                        .species(787)
                        .build();
                break;

            case "tapu fini":
            case "tapufini":
                pokemon = PokemonBuilder.builder()
                        .species(788)
                        .build();
                break;

            case "sirfetchd":
            case "sirfetch'd":
                pokemon = PokemonBuilder.builder()
                        .species(865)
                        .build();
                break;

            case "great tusk":
            case "greattusk":
                pokemon = PokemonBuilder.builder()
                        .species(984)
                        .build();
                break;

            case "scream tail":
            case "screamtail":
                pokemon = PokemonBuilder.builder()
                        .species(985)
                        .build();
                break;

            case "brute bonnet":
            case "brutebonnet":
                pokemon = PokemonBuilder.builder()
                        .species(986)
                        .build();
                break;

            case "flutter mane":
            case "fluttermane":
                pokemon = PokemonBuilder.builder()
                        .species(987)
                        .build();
                break;

            case "slither wing":
            case "slitherwing":
                pokemon = PokemonBuilder.builder()
                        .species(988)
                        .build();
                break;

            case "sandy shocks":
            case "sandyshocks":
                pokemon = PokemonBuilder.builder()
                        .species(989)
                        .build();
                break;

            case "iron treads":
            case "irontreads":
                pokemon = PokemonBuilder.builder()
                        .species(990)
                        .build();
                break;

            case "iron bundle":
            case "ironbundle":
                pokemon = PokemonBuilder.builder()
                        .species(991)
                        .build();
                break;

            case "iron hands":
            case "ironhands":
                pokemon = PokemonBuilder.builder()
                        .species(992)
                        .build();
                break;

            case "iron jugulis":
            case "ironjugulis":
                pokemon = PokemonBuilder.builder()
                        .species(993)
                        .build();
                break;

            case "iron moth":
            case "ironmoth":
                pokemon = PokemonBuilder.builder()
                        .species(994)
                        .build();
                break;

            case "iron thorns":
            case "ironthorns":
                pokemon = PokemonBuilder.builder()
                        .species(995)
                        .build();
                break;

            case "wo-chien":
            case "wo chien":
                pokemon = PokemonBuilder.builder()
                        .species(1001)
                        .build();
                break;

            case "chien-pao":
            case "chien pao":
                pokemon = PokemonBuilder.builder()
                        .species(1002)
                        .build();
                break;

            case "ting-lu":
            case "ting lu":
                pokemon = PokemonBuilder.builder()
                        .species(1003)
                        .build();
                break;

            case "chi-yu":
            case "chi yu":
                pokemon = PokemonBuilder.builder()
                        .species(1004)
                        .build();
                break;

            case "roaring moon":
            case "roaringmoon":
                pokemon = PokemonBuilder.builder()
                        .species(1005)
                        .build();
                break;

            case "iron valiant":
            case "ironvaliant":
                pokemon = PokemonBuilder.builder()
                        .species(1006)
                        .build();
                break;

            case "walking wake":
            case "walkingwake":
                pokemon = PokemonBuilder.builder()
                        .species(1009)
                        .build();
                break;

            case "iron leaves":
            case "ironleaves":
                pokemon = PokemonBuilder.builder()
                        .species(1010)
                        .build();
                break;

            case "gouging fire":
            case "gougingfire":
                pokemon = PokemonBuilder.builder()
                        .species(1020)
                        .build();
                break;

            case "raging bolt":
            case "ragingbolt":
                pokemon = PokemonBuilder.builder()
                        .species(1021)
                        .build();
                break;

            case "iron boulder":
            case "ironboulder":
                pokemon = PokemonBuilder.builder()
                        .species(1022)
                        .build();
                break;

            case "iron crown":
            case "ironcrown":
                pokemon = PokemonBuilder.builder()
                        .species(1023)
                        .build();
                break;

        }

        if (pokemon == null) {

            pokemon = PokemonBuilder.builder()
                    .species(species)
                    .build();

        }

        return pokemon;

    }

    public static Pokemon rebuildPokemon (CompoundNBT nbt, String teraType, String mythic) {

        //Pokemon pokemon = rebuildPokemonWithStupidAssName(species);
        Pokemon pokemon = PokemonFactory.create(nbt);

        if (teraType != null) {

            if (ModList.get().isLoaded("catalystterapokemon")) {

                NBTHelpers.setTeraType(pokemon, teraType, false);

            }

        }

        if (mythic != null) {

            if (ModList.get().isLoaded("pokemonmythology")) {

                MythicHandler.setMythic(pokemon, MythicHandler.getFromName(mythic), false);

            }

        }
        return pokemon;

        /*for (String s : specs) {

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

                        case "times cloned":
                            pokemon.getPersistentData().putInt("NumCloned", Integer.parseInt(value));
                            break;

                        case "enchants":
                            pokemon.getPersistentData().putInt("NumEnchanted", Integer.parseInt(value));
                            break;

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
        return pokemon;*/

    }

    private static String getTranslatedSpec (String spec) {

        String returnedValue = spec;
        for (Map.Entry<String, String> entry : ConfigGetters.translationMap.entrySet()) {

            if (spec.equalsIgnoreCase(entry.getKey())) returnedValue = entry.getValue();

        }

        return returnedValue;

    }

}
