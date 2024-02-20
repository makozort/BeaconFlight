package net.makozort.beaconflight.content.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.entity.custom.TestEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;

public class TestRenderer extends MobRenderer<TestEntity, CustomModel<TestEntity>> {
    public TestRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CustomModel<>(pContext.bakeLayer(ModModelLayers.TEST_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(TestEntity pEntity) {
        return new ResourceLocation(BeaconFlight.MOD_ID, "textures/entity/texture.png");
    }

    @Override
    public void render(TestEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
