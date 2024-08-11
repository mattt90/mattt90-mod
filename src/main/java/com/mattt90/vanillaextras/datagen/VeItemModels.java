package com.mattt90.vanillaextras.datagen;

import com.mattt90.vanillaextras.Registration;
import com.mattt90.vanillaextras.VanillaExtras;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VeItemModels extends ItemModelProvider {

    public VeItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VanillaExtras.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.TEST_BLOCK.getId().getPath(), modLoc("block/test_block"));
    }
}
