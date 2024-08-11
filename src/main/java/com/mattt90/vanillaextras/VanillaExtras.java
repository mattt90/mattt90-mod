package com.mattt90.vanillaextras;

import com.mattt90.vanillaextras.datagen.DataGeneration;
import com.mattt90.vanillaextras.network.Channel;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(VanillaExtras.MODID)
public class VanillaExtras {

    public static final String MODID = "vanillaextras";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VanillaExtras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.init(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGeneration::generate);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Channel.register();
    }
}
