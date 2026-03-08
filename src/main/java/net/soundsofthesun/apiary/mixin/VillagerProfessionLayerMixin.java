package net.soundsofthesun.apiary.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.Identifier;
import net.soundsofthesun.apiary.Apiary;
import net.soundsofthesun.apiary.client.model.BeekeeperVillagerModel;
import net.soundsofthesun.apiary.entity.ModVillagers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerProfessionLayer.class)
public abstract class VillagerProfessionLayerMixin {
    private final @Unique VillagerModel beekeeperModel = new VillagerModel(BeekeeperVillagerModel.createBodyModel().getRoot().bake(128, 128));
    @WrapOperation(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/VillagerProfessionLayer;renderColoredCutoutModel(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/Identifier;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V",
            ordinal = 0))
    void sun$renderBeekeeperHood(@SuppressWarnings("rawtypes") Model model, Identifier identifier, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, LivingEntityRenderState livingEntityRenderState, int ii, int iii, Operation<Void> original) {
        if (livingEntityRenderState instanceof VillagerRenderState state && state.villagerData != null && state.villagerData.profession().is(ModVillagers.BEEKEEPER)) {
            original.call(beekeeperModel, Apiary.id("textures/entity/villager/beekeeper_veil.png"), poseStack, submitNodeCollector, i, state, ii, iii);
        } else {
            original.call(model, identifier, poseStack, submitNodeCollector, i, livingEntityRenderState, ii, iii);
        }
    }
}
