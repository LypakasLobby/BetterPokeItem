package com.lypaka.betterpokeitem;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = BetterPokeItem.MOD_ID)
public class InteractionListener {

    @SubscribeEvent
    public static void onItemClick (PlayerInteractEvent.RightClickItem event) {

        if (event.getSide() == LogicalSide.CLIENT) return;
        if (event.getHand() == Hand.OFF_HAND) return;

        System.out.println("hi");
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        ItemStack item = event.getItemStack();
        if (item.getItem().getRegistryName().toString().equalsIgnoreCase("pixelmon:pixelmon_sprite")) {

            if (!player.getHeldItem(Hand.MAIN_HAND).getDisplayName().getString().contains("PokeItem")) return;
            System.out.println("hi 2");
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

                    Pokemon pokemon = Utils.rebuildPokemon(species, specs);
                    player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount() - 1);
                    PlayerPartyStorage storage = StorageProxy.getParty(player);
                    storage.add(pokemon);
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

                Pokemon pokemon = Utils.rebuildPokemon(species, specs);
                player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount() - 1);
                PlayerPartyStorage storage = StorageProxy.getParty(player);
                storage.add(pokemon);
                player.sendMessage(FancyText.getFormattedText("&aYou've un-itemized your " + pokemon.getLocalizedName() + "!"), player.getUniqueID());

            }

        }

    }

}
