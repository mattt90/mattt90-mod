package com.mattt90.vanillaextras.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import com.mattt90.vanillaextras.Registration;
import com.mattt90.vanillaextras.VanillaExtras;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

public class VeBlockTags extends BlockTagsProvider {

    public VeBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VanillaExtras.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(Registration.TEST_BLOCK.get());
     }
}
