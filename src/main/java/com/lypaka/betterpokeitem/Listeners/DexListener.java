package com.lypaka.betterpokeitem.Listeners;

import com.pixelmonmod.pixelmon.api.events.PokedexEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DexListener {

    @SubscribeEvent
    public void onDex (PokedexEvent.Pre event) {

        Pokemon pokemon = event.getPokemon();
        if (pokemon.getPersistentData().contains("PreventDex")) {

            event.setCanceled(true);

        }

    }

}
