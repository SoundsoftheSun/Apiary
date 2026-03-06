package net.soundsofthesun.apiary.blocks.extractor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
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
import org.jspecify.annotations.Nullable;

public class ExtractorBlockEntity extends BlockEntity implements ImplementedContainer, WorldlyContainer {
    public ExtractorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.HONEY_EXTRACTOR_ENTITY, pos, blockState);
    }

    //TODO tick behavior and for some reason the inventory isnt saving

    public static void tick(Level level, BlockPos pos, BlockState blockState, ExtractorBlockEntity be) {
        if (be.getItem(0).getCount() == 4) {
            Direction facing = blockState.getValue(HorizontalDirectionalBlock.FACING);
            float x = 0.5F;
            float y = 0.0625F;
            float z = 0.5F;
            if (facing == Direction.NORTH) z = -0.185F;
            else if (facing == Direction.SOUTH) z = 1.185F;
            else if (facing == Direction.EAST) x = 1.185F;
            else if (facing == Direction.WEST) x = -0.185F;
            level.addParticle(ParticleTypes.FALLING_HONEY, pos.getX()+x, pos.getY()+y, pos.getZ()+z, 1, 1, 1);
        }
    }

    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, getItems());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        ContainerHelper.loadAllItems(input, items);
        super.loadAdditional(input);
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
