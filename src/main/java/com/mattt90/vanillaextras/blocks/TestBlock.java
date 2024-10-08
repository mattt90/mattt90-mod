package com.mattt90.vanillaextras.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
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
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo.Map;

import com.mattt90.vanillaextras.VanillaExtras;

import java.util.stream.Collectors;

public class TestBlock extends Block implements EntityBlock {

    private static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public TestBlock() {
        super(Properties.of()
            .strength(1.0F)
            .sound(SoundType.GRASS));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TestBlockEntity(blockPos, blockState);
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        } else {
            return (lvl, pos, st, be) -> {
                if (be instanceof TestBlockEntity t && Math.random() > 0.995) {
                    BlockPos bPos = t.getBlockPos();
                    addOneEntity(level, bPos);
                }
            };
        }
    }

    private void addOneEntity(Level level, BlockPos bPos) {
        BlockState bState = level.getBlockState(new BlockPos(bPos.getX(), bPos.getY() - 1, bPos.getZ()));
        BlockPos summonBlockPos = new BlockPos(bPos.getX(), bPos.getY() + 1, bPos.getZ());
        // TODO: config

        switch (bState.toString()) {
            case "Block{minecraft:dirt}":
                summonEntity(level, summonBlockPos, "minecraft:chicken");
                break;
            case "Block{minecraft:cobblestone}":
                summonEntity(level, summonBlockPos, "minecraft:cow");
                break;
            case "Block{minecraft:air}":
                // TODO: breeze
                summonEntity(level, summonBlockPos, "minecraft:pig");
                break;
            case "Block{minecraft:water}[level=0]":
                summonEntity(level, summonBlockPos, "minecraft:cod");
                break;
            default:
                break;
        }
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
