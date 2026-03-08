package net.soundsofthesun.apiary.blocks.extractor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.soundsofthesun.apiary.blocks.ImplementedContainer;
import net.soundsofthesun.apiary.blocks.ModBlockEntities;
import net.soundsofthesun.apiary.blocks.ModBlocks;
import net.soundsofthesun.apiary.blocks.ModProperties;
import org.jspecify.annotations.Nullable;

public class ExtractorBlockEntity extends BlockEntity implements ImplementedContainer, WorldlyContainer {
    public ExtractorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.HONEY_EXTRACTOR_ENTITY, pos, blockState);
    }

    public int ticks = 0;
    private final int runTicks = 200;

    public static void tick(Level level, BlockPos pos, BlockState state, ExtractorBlockEntity be) {
        if (!(level instanceof ServerLevel serverLevel) || state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.OFF) return;
        be.ticks++;
        if (!(be.ticks % 20 == 0)) return;
        if (state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.ON) {
            drip(serverLevel, pos, state);
            if (be.ticks >= be.runTicks) {
                if (dripToPan(serverLevel, pos, state)) {
                    be.ticks = 0;
                    level.setBlockAndUpdate(pos, state.setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.OFF));
                } else {
                    be.ticks = be.runTicks/2;
                }
            }

        }
    }

    private static boolean dripToPan(ServerLevel level, BlockPos pos, BlockState blockState) {
        BlockPos pan = findNearestDripPan(level, pos, blockState);
        if (pan != null) {
            BlockState state = level.getBlockState(pan);
            if (state.getValue(ModProperties.ACTIVE_PROPERTY) == ModProperties.ACTIVE_STATE.OFF) {
                level.setBlockAndUpdate(pan, state.setValue(ModProperties.ACTIVE_PROPERTY, ModProperties.ACTIVE_STATE.ON));
                level.playSound(null, pan, SoundEvents.HONEY_BLOCK_BREAK, SoundSource.BLOCKS, 0.5F, 1F);
                return true;
            }
        }
        return false;
    }

    private static @Nullable BlockPos findNearestDripPan(Level level, BlockPos pos, BlockState blockState) {
        BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
        m.move(blockState.getValue(HorizontalDirectionalBlock.FACING));
        for (int i = 0; i < 6; i++) {
            m.move(Direction.DOWN);
            if (level.getBlockState(m).is(ModBlocks.DRIP_PAN)) {
                return m.immutable();
            }
        }
        return null;
    }

    private static final float yOffset = 0.0625F;
    private static void drip(ServerLevel level, BlockPos pos, BlockState blockState) {
        float x = 0.5F;
        float z = 0.5F;
        switch (blockState.getValue(HorizontalDirectionalBlock.FACING)) {
            case NORTH -> z = -0.185F;
            case SOUTH -> z = 1.185F;
            case EAST -> x = 1.185F;
            case WEST -> x = -0.185F;
        }
        level.sendParticles(ParticleTypes.FALLING_HONEY, pos.getX()+x, pos.getY()+yOffset, pos.getZ()+z, 1, 0, 0, 0, 1);
    }

    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, getItems());
        output.putInt("ticks", ticks);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        ticks = input.getIntOr("ticks", 0);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return false;
    }
}
