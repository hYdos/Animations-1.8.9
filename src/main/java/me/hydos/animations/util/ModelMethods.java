/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package me.hydos.animations.util;

import me.hydos.animations.Animations;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMethods {
    private static final FieldWrapper<ModelRenderer> bipedCape = new FieldWrapper(Animations.isObfuscated ? "field_178729_w" : "bipedCape", ModelPlayer.class);

    /*
     * Unable to fully structure code
     */
    public static void setRotationAnglesModelBiped(ModelBiped model, float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {
        model.bipedHead.rotateAngleY = p_78087_4_ / 57.295776f;
        model.bipedHead.rotateAngleX = p_78087_5_ / 57.295776f;
        model.bipedRightArm.rotateAngleX = MathHelper.cos((float)(p_78087_1_ * 0.6662f + 3.1415927f)) * 2.0f * p_78087_2_ * 0.5f;
        model.bipedLeftArm.rotateAngleX = MathHelper.cos((float)(p_78087_1_ * 0.6662f)) * 2.0f * p_78087_2_ * 0.5f;
        model.bipedRightArm.rotateAngleZ = 0.0f;
        model.bipedLeftArm.rotateAngleZ = 0.0f;
        model.bipedRightLeg.rotateAngleX = MathHelper.cos((float)(p_78087_1_ * 0.6662f)) * 1.4f * p_78087_2_;
        model.bipedLeftLeg.rotateAngleX = MathHelper.cos((float)(p_78087_1_ * 0.6662f + 3.1415927f)) * 1.4f * p_78087_2_;
        model.bipedRightLeg.rotateAngleY = 0.0f;
        model.bipedLeftLeg.rotateAngleY = 0.0f;
        if (model.isRiding) {
            model.bipedRightArm.rotateAngleX += -0.62831855f;
            model.bipedLeftArm.rotateAngleX += -0.62831855f;
            model.bipedRightLeg.rotateAngleX = -1.2566371f;
            model.bipedLeftLeg.rotateAngleX = -1.2566371f;
            model.bipedRightLeg.rotateAngleY = 0.31415927f;
            model.bipedLeftLeg.rotateAngleY = -0.31415927f;
        }
        if (model.heldItemLeft != 0) {
            model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5f - 0.31415927f * (float)model.heldItemLeft;
        }
        model.bipedRightArm.rotateAngleY = 0.0f;
        model.bipedRightArm.rotateAngleZ = 0.0f;
        switch (model.heldItemRight) {
            default: {
                break;
            }
            case 1: {
                model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5f - 0.31415927f * (float)model.heldItemRight;
                break;
            }
            case 3: 
        }
        model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5f - 0.31415927f * (float)model.heldItemRight;
        model.bipedRightArm.rotateAngleY = 0.0f;
// 3 sources:
        model.bipedLeftArm.rotateAngleY = 0.0f;
        if (model.swingProgress > -9990.0f) {
            float f = model.swingProgress;
            float f1 = 0;
            float f2 = 0;
            model.bipedBody.rotateAngleY = MathHelper.sin((float)(MathHelper.sqrt_float((float)f) * 3.1415927f * 2.0f)) * 0.2f;
            model.bipedRightArm.rotationPointZ = MathHelper.sin((float)model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedRightArm.rotationPointX = -MathHelper.cos((float)model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedLeftArm.rotationPointZ = -MathHelper.sin((float)model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedLeftArm.rotationPointX = MathHelper.cos((float)model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedRightArm.rotateAngleY += model.bipedBody.rotateAngleY;
            model.bipedLeftArm.rotateAngleY += model.bipedBody.rotateAngleY;
            model.bipedLeftArm.rotateAngleX += model.bipedBody.rotateAngleY;
            f = 1.0f - model.swingProgress;
            f *= f;
            f *= f;
            f = 1.0f - f;
            f1 = MathHelper.sin((float)(f * 3.1415927f));
            f2 = MathHelper.sin((float)(model.swingProgress * 3.1415927f)) * -(model.bipedHead.rotateAngleX - 0.7f) * 0.75f;
            model.bipedRightArm.rotateAngleX = (float)((double)model.bipedRightArm.rotateAngleX - ((double)f1 * 1.2 + (double)f2));
            model.bipedRightArm.rotateAngleY += model.bipedBody.rotateAngleY * 2.0f;
            model.bipedRightArm.rotateAngleZ += MathHelper.sin((float)(model.swingProgress * 3.1415927f)) * -0.4f;
        }
        if (model.isSneak) {
            model.bipedBody.rotateAngleX = 0.5f;
            model.bipedRightArm.rotateAngleX += 0.4f;
            model.bipedLeftArm.rotateAngleX += 0.4f;
            model.bipedRightLeg.rotationPointZ = 4.0f;
            model.bipedLeftLeg.rotationPointZ = 4.0f;
            model.bipedRightLeg.rotationPointY = 9.0f;
            model.bipedLeftLeg.rotationPointY = 9.0f;
            model.bipedHead.rotationPointY = 1.0f;
        } else {
            model.bipedBody.rotateAngleX = 0.0f;
            model.bipedRightLeg.rotationPointZ = 0.1f;
            model.bipedLeftLeg.rotationPointZ = 0.1f;
            model.bipedRightLeg.rotationPointY = 12.0f;
            model.bipedLeftLeg.rotationPointY = 12.0f;
            model.bipedHead.rotationPointY = 0.0f;
        }
        model.bipedRightArm.rotateAngleZ += MathHelper.cos((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
        model.bipedLeftArm.rotateAngleZ -= MathHelper.cos((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
        model.bipedRightArm.rotateAngleX += MathHelper.sin((float)(p_78087_3_ * 0.067f)) * 0.05f;
        model.bipedLeftArm.rotateAngleX -= MathHelper.sin((float)(p_78087_3_ * 0.067f)) * 0.05f;
        if (model.aimedBow) {
            float f3 = 0.0f;
            float f4 = 0.0f;
            model.bipedRightArm.rotateAngleZ = 0.0f;
            model.bipedLeftArm.rotateAngleZ = 0.0f;
            model.bipedRightArm.rotateAngleY = -(0.1f - f3 * 0.6f) + model.bipedHead.rotateAngleY;
            model.bipedLeftArm.rotateAngleY = 0.1f - f3 * 0.6f + model.bipedHead.rotateAngleY + 0.4f;
            model.bipedRightArm.rotateAngleX = -1.5707964f + model.bipedHead.rotateAngleX;
            model.bipedLeftArm.rotateAngleX = -1.5707964f + model.bipedHead.rotateAngleX;
            model.bipedRightArm.rotateAngleX -= f3 * 1.2f - f4 * 0.4f;
            model.bipedLeftArm.rotateAngleX -= f3 * 1.2f - f4 * 0.4f;
            model.bipedRightArm.rotateAngleZ += MathHelper.cos((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
            model.bipedLeftArm.rotateAngleZ -= MathHelper.cos((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
            model.bipedRightArm.rotateAngleX += MathHelper.sin((float)(p_78087_3_ * 0.067f)) * 0.05f;
            model.bipedLeftArm.rotateAngleX -= MathHelper.sin((float)(p_78087_3_ * 0.067f)) * 0.05f;
        }
        ModelBase.copyModelAngles((ModelRenderer)model.bipedHead, (ModelRenderer)model.bipedHeadwear);
    }

    public static void setRotationAnglesModelPlayer(ModelPlayer model, float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {
        ModelMethods.setRotationAnglesModelBiped((ModelBiped)model, p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);
        ModelBase.copyModelAngles((ModelRenderer)model.bipedLeftLeg, (ModelRenderer)model.bipedLeftLegwear);
        ModelBase.copyModelAngles((ModelRenderer)model.bipedRightLeg, (ModelRenderer)model.bipedRightLegwear);
        ModelBase.copyModelAngles((ModelRenderer)model.bipedLeftArm, (ModelRenderer)model.bipedLeftArmwear);
        ModelBase.copyModelAngles((ModelRenderer)model.bipedRightArm, (ModelRenderer)model.bipedRightArmwear);
        ModelBase.copyModelAngles((ModelRenderer)model.bipedBody, (ModelRenderer)model.bipedBodyWear);
        if (entityIn.isSneaking()) {
            ModelMethods.bipedCape.get((Object)model).rotationPointY = 2.0f;
            return;
        }
        ModelMethods.bipedCape.get((Object)model).rotationPointY = 0.0f;
    }

    public static void renderModelPlayer(ModelPlayer model, Entity entityIn, float scale) {
        ModelMethods.renderModelBiped((ModelBiped)model, entityIn, scale);
        GlStateManager.pushMatrix();
        if (model.isChild) {
            float f = 2.0f;
            GlStateManager.scale((float)(1.0f / f), (float)(1.0f / f), (float)(1.0f / f));
            GlStateManager.translate((float)0.0f, (float)(24.0f * scale), (float)0.0f);
            model.bipedLeftLegwear.render(scale);
            model.bipedRightLegwear.render(scale);
            model.bipedLeftArmwear.render(scale);
            model.bipedRightArmwear.render(scale);
            model.bipedBodyWear.render(scale);
        } else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            }
            model.bipedLeftLegwear.render(scale);
            model.bipedRightLegwear.render(scale);
            model.bipedLeftArmwear.render(scale);
            model.bipedRightArmwear.render(scale);
            model.bipedBodyWear.render(scale);
        }
        GlStateManager.popMatrix();
    }

    public static void renderModelBiped(ModelBiped model, Entity entityIn, float scale) {
        GlStateManager.pushMatrix();
        if (model.isChild) {
            float f = 2.0f;
            GlStateManager.scale((float)(1.5f / f), (float)(1.5f / f), (float)(1.5f / f));
            GlStateManager.translate((float)0.0f, (float)(16.0f * scale), (float)0.0f);
            model.bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)(1.0f / f), (float)(1.0f / f), (float)(1.0f / f));
            GlStateManager.translate((float)0.0f, (float)(24.0f * scale), (float)0.0f);
            model.bipedBody.render(scale);
            model.bipedRightArm.render(scale);
            model.bipedLeftArm.render(scale);
            model.bipedRightLeg.render(scale);
            model.bipedLeftLeg.render(scale);
            model.bipedHeadwear.render(scale);
        } else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            }
            model.bipedHead.render(scale);
            model.bipedBody.render(scale);
            model.bipedRightArm.render(scale);
            model.bipedLeftArm.render(scale);
            model.bipedRightLeg.render(scale);
            model.bipedLeftLeg.render(scale);
            model.bipedHeadwear.render(scale);
        }
        GlStateManager.popMatrix();
    }
}

