package net.dionysiachen.meilanzhuju.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.entity.MuskDeerEntity;
import net.dionysiachen.meilanzhuju.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MuskDeerRenderer extends MobRenderer<MuskDeerEntity, MuskDeerModel<MuskDeerEntity>> {
    private static final ResourceLocation MUSK_DEER_LOCATION = new ResourceLocation(MEILANZHUJU.MOD_ID,"textures/entity/musk_deer.png");

    public MuskDeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MuskDeerModel<>(pContext.bakeLayer(ModModelLayers.MUSK_DEER_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(MuskDeerEntity pEntity) {
        return MUSK_DEER_LOCATION;
    }

    @Override
    public void render(MuskDeerEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
