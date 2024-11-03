package com.mattt90.vanillaextras.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mattt90.vanillaextras.VanillaExtras;
import com.mojang.serialization.JsonOps;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

public class TestBlockManager extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private final Map<ResourceLocation, TestBlockData> testBlocks = new HashMap<>();

    public TestBlockManager() {
        super(GSON, "test_block_data"); // TODO: const
    }

    @Override
    protected void apply(@Nonnull Map<ResourceLocation, JsonElement> pObject, @Nonnull ResourceManager pResourceManager,
            @Nonnull ProfilerFiller pProfiler) {
        for (Map.Entry<ResourceLocation, JsonElement> entry : pObject.entrySet()) {
            ResourceLocation location = entry.getKey();

            TestBlockData.CODEC.decode(JsonOps.INSTANCE, entry.getValue()).get()
                    .ifLeft(testBlockData -> testBlocks.put(location, testBlockData.getFirst()))
                    .ifRight(error -> VanillaExtras.LOGGER.error("Failed to parse {} test block: {}", location, error.message()));
                    //.ifRight(error -> TODO: logger);
        }
    }
    
    public TestBlockData getData() {
        return testBlocks.get(new ResourceLocation(VanillaExtras.MODID,"test_block_data"));
    }

    public Optional<TestBlockSpawnData> getSpawnData(ResourceLocation block) {
        return getSpawnData(block.toString());
    }

    public Optional<TestBlockSpawnData> getSpawnData(String block) {
        TestBlockData data = getData();
        return data.data().stream().filter(d -> d.block().equals(block)).findFirst();
    }
}
