package net.soundsofthesun.beekeepingage.entity.flowergolem;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.soundsofthesun.beekeepingage.entity.ModEntities;
import net.soundsofthesun.beekeepingage.entity.goal.MoveToFlowerGoal;
import org.jspecify.annotations.NonNull;

public class FlowerGolem extends PathfinderMob {

    public FlowerGolem(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.setState(FlowerGolemState.IDLE);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(PathType.DANGER_OTHER, 16.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
    }

    private boolean doPickAnim = true;

    private ItemStack holding = ItemStack.EMPTY;

    public boolean isHoldingFlower() {
        return this.holding.is(ItemTags.FLOWERS);
    }

    public void setHolding(ItemStack stack) {
        if (!this.isHoldingFlower() && stack.is(ItemTags.FLOWERS)) {
            this.holding = stack;
            System.out.println("found flower: "+this.holding.getItemName());
            this.setState(FlowerGolemState.OFFER);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveToFlowerGoal(this, 1.5F, 16, 10));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    private void doIdleAnimation() {
        if (this.idleAnimTime < this.maxIdleAnimTime) {
            this.idleAnimTime++;
        } else {
            this.idleAnimTime = 0;
            this.rotateAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {

            if (this.doPickAnim && this.getState() == FlowerGolemState.OFFER) {
                this.offerAnimationState.start(this.tickCount);
                this.doPickAnim = false;
            }

            doIdleAnimation();
        }
    }

    private int idleAnimTime = 0;
    private int maxIdleAnimTime = 200;

    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState rotateAnimationState = new AnimationState();
    public final AnimationState shakeAnimationState = new AnimationState();
    public final AnimationState offerAnimationState = new AnimationState();

    private static final EntityDataAccessor<FlowerGolemState> FLOWER_GOLEM_STATE = SynchedEntityData.defineId(
            FlowerGolem.class, ModEntities.FLOWER_GOLEM_STATE
    );

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.2F).add(Attributes.STEP_HEIGHT, 1.0).add(Attributes.MAX_HEALTH, 12.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FLOWER_GOLEM_STATE, FlowerGolemState.IDLE);
    }

    public FlowerGolemState getState() {
        return this.entityData.get(FLOWER_GOLEM_STATE);
    }

    public void setState(FlowerGolemState state) {
        this.entityData.set(FLOWER_GOLEM_STATE, state, true);
    }

}
