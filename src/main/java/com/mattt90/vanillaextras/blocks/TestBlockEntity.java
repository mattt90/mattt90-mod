package com.mattt90.vanillaextras.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.mattt90.vanillaextras.Registration;

public class TestBlockEntity extends BlockEntity {

    public TestBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.TEST_BLOCK_ENTITY.get(), pos, state);
    }

}
