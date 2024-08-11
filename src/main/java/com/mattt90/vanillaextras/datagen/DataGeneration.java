package com.mattt90.vanillaextras.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGeneration {

    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new VeBlockStates(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new VeItemModels(packOutput, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new VeRecipes(packOutput));
    }
}
