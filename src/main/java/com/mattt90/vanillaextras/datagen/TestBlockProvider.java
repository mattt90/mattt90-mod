package com.mattt90.vanillaextras.datagen;

import com.mattt90.vanillaextras.VanillaExtras;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBlockProvider extends JsonCodecProvider<TestBlockData> {

    public TestBlockProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper, VanillaExtras.MODID, JsonOps.INSTANCE, PackType.SERVER_DATA, "test_block_data", TestBlockData.CODEC, getTestBlockData());
    }

    public static Map<ResourceLocation, TestBlockData> getTestBlockData() {
        Map<ResourceLocation, TestBlockData> testBlockData = new HashMap<>();
        testBlockData.put(
            new ResourceLocation(VanillaExtras.MODID,"test_block_data"),
            new TestBlockData(
                0.995,
                List.of(
                    new TestBlockSpawnData("minecraft:dirt", "minecraft:chicken"),
                    new TestBlockSpawnData("minecraft:cobblestone", "minecraft:cow"),
                    new TestBlockSpawnData("minecraft:air", "minecraft:pig"),
                    new TestBlockSpawnData("minecraft:water", "minecraft:cod")
                )));

        return testBlockData;
    }
}