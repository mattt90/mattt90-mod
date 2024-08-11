package com.mattt90.vanillaextras.datagen;

import com.mattt90.vanillaextras.Registration;
import com.mattt90.vanillaextras.VanillaExtras;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class VeBlockStates extends BlockStateProvider {

    public VeBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VanillaExtras.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerDefault(Registration.TEST_BLOCK, "test_block");
    }

    private void registerDefault(RegistryObject<? extends Block> obj, String blockResourceLocation) {
        ResourceLocation bottom = new ResourceLocation(VanillaExtras.MODID, "block/" + blockResourceLocation + "_bottom");
        ResourceLocation top = new ResourceLocation(VanillaExtras.MODID, "block/" + blockResourceLocation + "_top");
        ResourceLocation side = new ResourceLocation(VanillaExtras.MODID,"block/" + blockResourceLocation + "_side");
        BlockModelBuilder model = models()
            .cubeBottomTop(
                obj.getId().getPath(),
                side,
                bottom,
                top)
            .texture("particle", side);
        getVariantBuilder(obj.get()).forAllStates(state -> {
            ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
            bld.modelFile(model);
            return bld.build();
        });
    }
}