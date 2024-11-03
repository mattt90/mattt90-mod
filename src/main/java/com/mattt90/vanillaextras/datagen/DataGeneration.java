package com.mattt90.vanillaextras.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DataGeneration {

    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new TestBlockProvider(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new VeBlockStates(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new VeItemModels(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new VeLanguageProvider(packOutput, "en_us"));

        VeBlockTags blockTags = new VeBlockTags(packOutput, lookupProvider, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new VeRecipes(packOutput));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(VeLootTables::new, LootContextParamSets.BLOCK))));
    }
}
