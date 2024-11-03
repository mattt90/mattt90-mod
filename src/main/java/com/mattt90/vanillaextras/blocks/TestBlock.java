package com.mattt90.vanillaextras.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

import org.jetbrains.annotations.Nullable;

import com.mattt90.vanillaextras.VanillaExtras;
import com.mattt90.vanillaextras.datagen.TestBlockData;
import com.mattt90.vanillaextras.datagen.TestBlockSpawnData;

import java.util.Optional;

import javax.annotation.Nonnull;

public class TestBlock extends Block implements EntityBlock {

    private static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public TestBlock() {
        super(Properties.of()
            .strength(1.0F)
            .sound(SoundType.GRASS));
    }

    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return AABB;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        return new TestBlockEntity(blockPos, blockState);
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        } else {
            return (lvl, pos, st, be) -> {

                if (be instanceof TestBlockEntity t) {
                    TestBlockData testBlockData = VanillaExtras.TEST_BLOCK_MANAGER.getData();
                    if (Math.random() > testBlockData.spawnRate()) {
                        BlockPos bPos = t.getBlockPos();
                        addOneEntity(level, bPos);
                    }
                }
            };
        }
    }

    
    private void addOneEntity(Level level, BlockPos bPos) {
        BlockState bState = level.getBlockState(new BlockPos(bPos.getX(), bPos.getY() - 1, bPos.getZ()));
        Block block = bState.getBlock();
        var blockName = ForgeRegistries.BLOCKS.getKey(block);
        Optional<TestBlockSpawnData> testBlockSpawnData = VanillaExtras.TEST_BLOCK_MANAGER.getSpawnData(blockName);

        if (!testBlockSpawnData.isPresent()) {
            return;
        }

        BlockPos summonBlockPos = new BlockPos(bPos.getX(), bPos.getY() + 1, bPos.getZ());
        summonEntity(level, summonBlockPos, testBlockSpawnData.get().mob());
    }

    private void summonEntity(Level l, BlockPos bPos, String mobId) {
        CompoundTag compoundtag = new CompoundTag();
        compoundtag.putString("id", mobId);
            
        Entity entity = EntityType.loadEntityRecursive(compoundtag, l, (e) -> {
                e.moveTo(bPos.getX(), bPos.getY(), bPos.getZ(), 0, 0);;
                return e;
        });
        l.addFreshEntity(entity);
    }
}
