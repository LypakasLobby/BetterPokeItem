package com.lypaka.betterpokeitem.Listeners;

import com.lypaka.betterpokeitem.BetterPokeItem;
import com.lypaka.betterpokeitem.ConfigGetters;
import com.lypaka.betterpokeitem.Utils;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class InteractionListener {

    @SubscribeEvent
    public void onItemClick (PlayerInteractEvent.RightClickItem event) {

        if (event.getSide() == LogicalSide.CLIENT) return;
        if (event.getHand() == Hand.OFF_HAND) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        ItemStack item = event.getItemStack();
        if (item.getItem().getRegistryName().toString().equalsIgnoreCase("pixelmon:pixelmon_sprite")) {

            if (!event.getItemStack().getOrCreateTag().contains("IsPokeItem")) return;
            ListNBT lore = item.getOrCreateChildTag("display").getList("Lore", 8);
            if (lore.toString().contains("Locked")) {

                if (!lore.toString().contains(player.getUniqueID().toString())) {

                    event.setCanceled(true);

                } else if (lore.toString().contains(player.getUniqueID().toString()) || PermissionHandler.hasPermission(player, "betterpokeitem.item.bypass")) {

                    String species = item.getDisplayName().getString();
                    species = species.substring(2);
                    List<String> specs = new ArrayList<>();
                    for (int i = 0; i < lore.size(); i++) {

                        String spec = lore.get(i).getString()
                                .replace("{\"text\":\"", "")
                                .replace("\"}", "");
                        specs.add(spec);

                    }

                    CompoundNBT nbt = item.getOrCreateChildTag("PokemonData");
                    String teraType = null;
                    if (item.getOrCreateTag().contains("TeraType")) teraType = item.getOrCreateTag().getString("TeraType");
                    String mythic = null;
                    if (item.getOrCreateTag().contains("Mythic")) mythic = item.getOrCreateTag().getString("Mythic");
                    Pokemon pokemon = Utils.rebuildPokemon(nbt, teraType, mythic);
                    player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount() - 1);
                    PlayerPartyStorage storage = StorageProxy.getParty(player);
                    if (ConfigGetters.preventDexEntry) {

                        pokemon.getPersistentData().putBoolean("PreventDex", true);

                    }
                    storage.add(pokemon);
                    BetterPokeItem.logger.info(player.getName().getString() + " un-itemized a " + pokemon.getLocalizedName() + "!");
                    player.sendMessage(FancyText.getFormattedText("&aYou've un-itemized your " + pokemon.getLocalizedName() + "!"), player.getUniqueID());

                }

            } else {

                String species = item.getDisplayName().getString();
                species = species.substring(2);
                List<String> specs = new ArrayList<>();
                for (int i = 0; i < lore.size(); i++) {

                    String spec = lore.get(i).getString()
                            .replace("{\"text\":\"", "")
                            .replace("\"}", "");
                    specs.add(spec);

                }

                CompoundNBT nbt = item.getOrCreateChildTag("PokemonData");
                String teraType = null;
                if (item.getOrCreateTag().contains("TeraType")) teraType = item.getOrCreateTag().getString("TeraType");
                String mythic = null;
                if (item.getOrCreateTag().contains("Mythic")) mythic = item.getOrCreateTag().getString("Mythic");
                Pokemon pokemon = Utils.rebuildPokemon(nbt, teraType, mythic);
                player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount() - 1);
                PlayerPartyStorage storage = StorageProxy.getParty(player);
                if (ConfigGetters.preventDexEntry) {

                    pokemon.getPersistentData().putBoolean("PreventDex", true);

                }
                storage.add(pokemon);
                BetterPokeItem.logger.info(player.getName().getString() + " un-itemized a " + pokemon.getLocalizedName() + "!");
                player.sendMessage(FancyText.getFormattedText("&aYou've un-itemized your " + pokemon.getLocalizedName() + "!"), player.getUniqueID());

            }

        }

    }

}
