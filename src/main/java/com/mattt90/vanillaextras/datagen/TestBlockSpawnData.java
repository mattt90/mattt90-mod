package com.mattt90.vanillaextras.datagen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TestBlockSpawnData(String block, String mob) {
    public static final Codec<TestBlockSpawnData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.STRING.fieldOf("block").forGetter(TestBlockSpawnData::block),
                    Codec.STRING.fieldOf("mob").forGetter(TestBlockSpawnData::mob)
            ).apply(instance, TestBlockSpawnData::new)
    );
}