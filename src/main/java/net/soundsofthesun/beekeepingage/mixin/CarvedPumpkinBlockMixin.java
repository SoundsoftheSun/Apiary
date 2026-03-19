package net.soundsofthesun.beekeepingage.mixin;

import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlockMixin extends HorizontalDirectionalBlock {
    // Add flower golem spawning functionality
    protected CarvedPumpkinBlockMixin(Properties properties) {
        super(properties);
    }

//    @Shadow
//    private static void spawnGolemInWorld(Level level, BlockPattern.BlockPatternMatch patternMatch, Entity golem, BlockPos pos) {
//    }
//
//    @Unique
//    @Nullable
//    private BlockPattern flowerGolemBase;
//    @Unique
//    @Nullable
//    private BlockPattern flowerGolemFull;
//    @Unique
//    private static final Predicate<BlockState> PUMPKIN = blockState -> blockState.is(Blocks.CARVED_PUMPKIN) || blockState.is(Blocks.JACK_O_LANTERN);
//
//    @Unique
//    private BlockPattern getOrCreateFlowerGolemBase() {
//        if (this.flowerGolemBase == null) {
//            this.flowerGolemBase = BlockPatternBuilder.start()
//                    .aisle("~ ~", "###", "~X~")
//                    .where('X', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SMOOTH_STONE)))
//                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.MOSS_BLOCK)))
//                    .where('~', BlockInWorld.hasState(BlockBehaviour.BlockStateBase::isAir))
//                    .build();
//        }
//        return this.flowerGolemBase;
//    }
//
//    @Unique
//    private BlockPattern getOrCreateFlowerGolemFull() {
//        if (this.flowerGolemFull == null) {
//            this.flowerGolemFull = BlockPatternBuilder.start()
//                    .aisle("~^~", "###", "~X~")
//                    .where('^', BlockInWorld.hasState(PUMPKIN))
//                    .where('X', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SMOOTH_STONE)))
//                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.MOSS_BLOCK)))
//                    .where('~', BlockInWorld.hasState(BlockBehaviour.BlockStateBase::isAir))
//                    .build();
//        }
//
//        return this.flowerGolemFull;
//    }
//
//    @WrapMethod(method = "canSpawnGolem")
//    boolean bka$canSpawnGolem(LevelReader level, BlockPos pos, Operation<Boolean> original) {
//        return original.call(level, pos) || this.getOrCreateFlowerGolemBase().find(level, pos) != null;
//    }
//
//    @WrapMethod(method = "trySpawnGolem")
//    void bka$trySpawnGolem(Level level, BlockPos pos, Operation<Void> original) {
//        original.call(level, pos);
//        BlockPattern.BlockPatternMatch flowerGolemPattern = this.getOrCreateFlowerGolemFull().find(level, pos);
//        if (flowerGolemPattern != null) {
//            FlowerGolem flowerGolem = ModEntities.FLOWER_GOLEM.create(level, EntitySpawnReason.TRIGGERED);
//            if (flowerGolem != null) {
//                spawnGolemInWorld(level, flowerGolemPattern, flowerGolem, flowerGolemPattern.getBlock(1, 2, 0).getPos());
//                return;
//            }
//        }
//    }

}
