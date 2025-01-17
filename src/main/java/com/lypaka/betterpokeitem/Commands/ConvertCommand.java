package com.lypaka.betterpokeitem.Commands;

import com.lypaka.betterpokeitem.ConfigGetters;
import com.lypaka.betterpokeitem.Utils;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.extraStats.LakeTrioStats;
import com.pixelmonmod.pixelmon.api.pokemon.stats.extraStats.MewStats;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.ModList;

public class ConvertCommand {

    public ConvertCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : BetterPokeItemCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("convert")
                                            .then(
                                                    Commands.argument("slot", IntegerArgumentType.integer(1))
                                                            .then(
                                                                    Commands.argument("lock", StringArgumentType.word())
                                                                            .executes(c -> {

                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                    if (!ConfigGetters.commandPermission.equalsIgnoreCase("")) {

                                                                                        if (!PermissionHandler.hasPermission(player, ConfigGetters.commandPermission)) {

                                                                                            player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                            return 0;

                                                                                        }

                                                                                    }

                                                                                    int slot = IntegerArgumentType.getInteger(c, "slot");
                                                                                    boolean lock = Boolean.parseBoolean(StringArgumentType.getString(c, "lock"));
                                                                                    slot = slot - 1;
                                                                                    PlayerPartyStorage storage = StorageProxy.getParty(player);
                                                                                    if (storage.countAblePokemon() == 1) {

                                                                                        player.sendMessage(FancyText.getFormattedText("&cCan't convert your only Pokemon!"), player.getUniqueID());
                                                                                        return 0;

                                                                                    }
                                                                                    Pokemon pokemon = storage.get(slot);
                                                                                    if (pokemon == null) {

                                                                                        player.sendMessage(FancyText.getFormattedText("&cNo Pokemon in this slot!"), player.getUniqueID());
                                                                                        return 0;

                                                                                    }

                                                                                    // I hate doing shit this way because of lazy typers, lol
                                                                                    for (String s : ConfigGetters.pokemonBlacklist) {

                                                                                        if (pokemon.getLocalizedName().equalsIgnoreCase(s)) {

                                                                                            player.sendMessage(FancyText.getFormattedText("&cPokemon is blacklisted from conversion!"), player.getUniqueID());
                                                                                            return 0;

                                                                                        }

                                                                                    }

                                                                                    // just as a safety measure
                                                                                    String pokemonName = pokemon.getSpecies().getName();
                                                                                    if (!pokemonName.equalsIgnoreCase("Mew") && !pokemonName.equalsIgnoreCase("Azelf") && !pokemonName.equalsIgnoreCase("Mesprit") && !pokemonName.equalsIgnoreCase("Uxie")) {

                                                                                        ConfigGetters.itemStackLore.removeIf(e -> e.contains("%enchants%"));
                                                                                        ConfigGetters.itemStackLore.removeIf(e -> e.contains("%numCloned%"));

                                                                                    }

                                                                                    ItemStack sprite = Utils.buildItem(pokemon, player, lock);
                                                                                    player.addItemStackToInventory(sprite);
                                                                                    storage.set(slot, null);
                                                                                    player.sendMessage(FancyText.getFormattedText("&aSuccessfully itemized your " + pokemon.getLocalizedName() + "!"), player.getUniqueID());
                                                                                    return 1;

                                                                                }

                                                                                return 0;

                                                                            })
                                                            )
                                                            .executes(c -> {

                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                    if (!ConfigGetters.commandPermission.equalsIgnoreCase("")) {

                                                                        if (!PermissionHandler.hasPermission(player, ConfigGetters.commandPermission)) {

                                                                            player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                            return 0;

                                                                        }

                                                                    }

                                                                    int slot = IntegerArgumentType.getInteger(c, "slot");
                                                                    slot = slot - 1;
                                                                    PlayerPartyStorage storage = StorageProxy.getParty(player);
                                                                    if (storage.countAblePokemon() == 1) {

                                                                        player.sendMessage(FancyText.getFormattedText("&cCan't convert your only Pokemon!"), player.getUniqueID());
                                                                        return 0;

                                                                    }
                                                                    Pokemon pokemon = storage.get(slot);
                                                                    if (pokemon == null) {

                                                                        player.sendMessage(FancyText.getFormattedText("&cNo Pokemon in this slot!"), player.getUniqueID());
                                                                        return 0;

                                                                    }

                                                                    // I hate doing shit this way because of lazy typers, lol
                                                                    for (String s : ConfigGetters.pokemonBlacklist) {

                                                                        if (pokemon.getLocalizedName().equalsIgnoreCase(s)) {

                                                                            player.sendMessage(FancyText.getFormattedText("&cPokemon is blacklisted from conversion!"), player.getUniqueID());
                                                                            return 0;

                                                                        }

                                                                    }

                                                                    ItemStack sprite = Utils.buildItem(pokemon, player, false);
                                                                    player.addItemStackToInventory(sprite);
                                                                    storage.set(slot, null);
                                                                    player.sendMessage(FancyText.getFormattedText("&aSuccessfully itemized your " + pokemon.getLocalizedName() + "!"), player.getUniqueID());
                                                                    return 1;

                                                                }

                                                                return 0;

                                                            })
                                            )
                            )
            );

        }

    }

}
