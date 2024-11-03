package com.mattt90.vanillaextras.datagen;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TestBlockData(Double spawnRate, List<TestBlockSpawnData> data) {
    public static final Codec<TestBlockData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.DOUBLE.fieldOf("spawnRate").forGetter(TestBlockData::spawnRate),
                TestBlockSpawnData.CODEC.listOf().fieldOf("data").forGetter(TestBlockData::data)
            ).apply(instance, TestBlockData::new)
    );
}
