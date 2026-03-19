package net.soundsofthesun.beekeepingage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.soundsofthesun.beekeepingage.components.ModComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeehiveBlockEntity.class)
public abstract class BeehiveBlockEntityMixin extends BlockEntity {
    public BeehiveBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Unique
    public ModComponents.RestoredComponent restored = ModComponents.RestoredComponent.DEFAULT;

    @WrapOperation(method = "setChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;setChanged()V"))
    void bka$setChanged(BeehiveBlockEntity instance, Operation<Void> original) {
        // Custom setChanged behavior for conversion from AbandonedHive

        if (!this.restored.bool()) {
            ModComponents.RestoredComponent restoredComponent = instance.components().get(ModComponents.BEEHIVE_RESTORED);
            if (restoredComponent != null) {
                this.restored = restoredComponent;
            }
        }
        original.call(instance);
    }

    @WrapOperation(method = "releaseOccupant", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private static boolean bka$blockHoneyIfSmokey(Level instance, BlockPos pos, BlockState state, Operation<Boolean> original) {
        if (CampfireBlock.isSmokeyPos(instance, pos)) {
            // Block honey increase if smokey
            return false;
        } else if (instance.getBlockEntity(pos) instanceof BeehiveBlockEntityMixin be && instance.getRandom().nextInt(4) == 0 && be.restored.bool() && state.getValue(BeehiveBlock.HONEY_LEVEL) < 5) {
            // 25% chance for bonus honey if restored hive
            return original.call(instance, pos, state.setValue(BeehiveBlock.HONEY_LEVEL, state.getValue(BeehiveBlock.HONEY_LEVEL)+1));
        } else {
            return original.call(instance, pos, state);
        }
    }

    @WrapOperation(method = "loadAdditional", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;loadAdditional(Lnet/minecraft/world/level/storage/ValueInput;)V"))
    void bka$loadAdditional(BeehiveBlockEntity instance, ValueInput input, Operation<Void> original) {
        original.call(instance, input);
        this.restored = input.read("restored", ModComponents.RestoredComponent.CODEC).orElse(ModComponents.RestoredComponent.DEFAULT);
    }

    @WrapOperation(method = "saveAdditional", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;saveAdditional(Lnet/minecraft/world/level/storage/ValueOutput;)V"))
    void bka$saveAdditional(BeehiveBlockEntity instance, ValueOutput output, Operation<Void> original) {
        original.call(instance, output);
        output.store("restored", ModComponents.RestoredComponent.CODEC, this.restored);
    }

    @WrapOperation(method = "applyImplicitComponents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;applyImplicitComponents(Lnet/minecraft/core/component/DataComponentGetter;)V"))
    void bka$applyImplicitComponents(BeehiveBlockEntity instance, DataComponentGetter componentGetter, Operation<Void> original) {
        original.call(instance, componentGetter);
        this.restored = componentGetter.getOrDefault(ModComponents.BEEHIVE_RESTORED, ModComponents.RestoredComponent.DEFAULT);
    }

    @WrapOperation(method = "collectImplicitComponents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;collectImplicitComponents(Lnet/minecraft/core/component/DataComponentMap$Builder;)V"))
    void bka$collectImplicitComponents(BeehiveBlockEntity instance, DataComponentMap.Builder components, Operation<Void> original) {
        original.call(instance, components);
        components.set(ModComponents.BEEHIVE_RESTORED, this.restored);
    }

}
