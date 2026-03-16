package net.soundsofthesun.beekeepingage.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelReader;
import net.soundsofthesun.beekeepingage.entity.flowergolem.FlowerGolem;

import java.util.EnumSet;

public class MoveToFlowerGoal extends Goal {
    private static final int GIVE_UP_TICKS = 1200;
    private static final int STAY_TICKS = 200;
    private static final int INTERVAL_TICKS = 200;
    protected final FlowerGolem golem;
    public final double speedModifier;
    protected int nextStartTick;
    protected int tryTicks;
    private int maxStayTicks;
    protected BlockPos blockPos = BlockPos.ZERO;
    private boolean reachedTarget;
    private final int searchRange;
    private final int verticalSearchRange;
    protected int verticalSearchStart;

    public MoveToFlowerGoal(FlowerGolem mob, double speedModifier, int searchRange, int verticalSearchRange) {
        this.golem = mob;
        this.speedModifier = speedModifier;
        this.searchRange = searchRange;
        this.verticalSearchStart = 0;
        this.verticalSearchRange = verticalSearchRange;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.golem.isHoldingFlower()) return false;
        if (this.nextStartTick > 0) {
            this.nextStartTick--;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.golem);
            return this.findNearestBlock();
        }
    }

    protected int nextStartTick(PathfinderMob creature) {
        return reducedTickDelay(200 + creature.getRandom().nextInt(200));
    }

    @Override
    public boolean canContinueToUse() {
        if (this.isReachedTarget()) return false;
        return this.tryTicks >= -this.maxStayTicks && this.tryTicks <= 1200 && this.isValidTarget(this.golem.level(), this.blockPos);
    }

    @Override
    public void start() {
        this.moveMobToBlock();
        this.tryTicks = 0;
        this.maxStayTicks = STAY_TICKS;
    }

    protected void moveMobToBlock() {
        this.golem.getNavigation().moveTo(this.blockPos.getX() + 0.5, this.blockPos.getY() + 1, this.blockPos.getZ() + 0.5, this.speedModifier);
    }

    public double acceptedDistance() {
        return 1.5;
    }

    protected BlockPos getMoveToTarget() {
        return this.blockPos;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        BlockPos blockPos = this.getMoveToTarget();
        if (!blockPos.closerToCenterThan(this.golem.position(), this.acceptedDistance())) {
            this.reachedTarget = false;
            this.tryTicks++;
            if (this.shouldRecalculatePath()) {
                this.golem.getNavigation().moveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, this.speedModifier);
            }
        } else {
            this.reachedTarget = true;
            if (!this.golem.isHoldingFlower()) {
                this.golem.setHolding(this.golem.level().getBlockState(this.blockPos).getBlock().asItem().getDefaultInstance());
            }
            this.tryTicks--;
        }
    }

    public boolean shouldRecalculatePath() {
        return this.tryTicks % 40 == 0;
    }

    protected boolean isReachedTarget() {
        return this.reachedTarget;
    }

    protected boolean findNearestBlock() {
        int i = this.searchRange;
        int j = this.verticalSearchRange;
        BlockPos blockPos = this.golem.blockPosition();
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int k = this.verticalSearchStart; k <= j; k = k > 0 ? -k : 1 - k) {
            for (int l = 0; l < i; l++) {
                for (int m = 0; m <= l; m = m > 0 ? -m : 1 - m) {
                    for (int n = m < l && m > -l ? l : 0; n <= l; n = n > 0 ? -n : 1 - n) {
                        mutableBlockPos.setWithOffset(blockPos, m, k - 1, n);
                        if (this.golem.isWithinHome(mutableBlockPos) && this.isValidTarget(this.golem.level(), mutableBlockPos)) {
                            this.blockPos = mutableBlockPos;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).is(BlockTags.FLOWERS);
    }
}