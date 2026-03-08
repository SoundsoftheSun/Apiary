package net.soundsofthesun.apiary.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.npc.VillagerModel;

public class BeekeeperVillagerModel extends VillagerModel {

    public BeekeeperVillagerModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static MeshDefinition createBodyModel() {
        MeshDefinition meshDefinition = VillagerModel.createBodyModel();
        PartDefinition partDefinition = meshDefinition.getRoot();
        // Clear the model data, we still need to get the hat rim to anchor the hood to it
        PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);PartDefinition partDefinition3 = head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);PartDefinition rim = partDefinition3.addOrReplaceChild("hat_rim", CubeListBuilder.create(), PartPose.ZERO);head.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.ZERO);PartDefinition partDefinition4 = partDefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);partDefinition4.addOrReplaceChild("jacket", CubeListBuilder.create(), PartPose.ZERO);partDefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.ZERO);partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
        // Nose clips a little bit, not a big deal for now
        rim.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.0F, -6.0F, 12.0F, 8.0F, 12.0F), PartPose.ZERO);
        return meshDefinition;
    }

}
