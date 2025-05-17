package com.lypaka.betterpokeitem.Listeners;

import com.lypaka.betterpokeitem.BetterPokeItem;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = BetterPokeItem.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) {

        Pixelmon.EVENT_BUS.register(new DexListener());
        MinecraftForge.EVENT_BUS.register(new InteractionListener());

    }

}
