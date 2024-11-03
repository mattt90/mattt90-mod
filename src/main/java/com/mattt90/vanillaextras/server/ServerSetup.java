package com.mattt90.vanillaextras.server;

import com.mattt90.vanillaextras.datagen.TestBlockManager;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerSetup {
    @SubscribeEvent
    public static void onReloadListeners(AddReloadListenerEvent event){
        event.addListener(new TestBlockManager());
    }
}