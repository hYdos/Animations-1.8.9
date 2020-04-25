/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.entity.layers.LayerArmorBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package me.hydos.animations.util;

import me.hydos.animations.Animations;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class CustomLayerBipedArmor
extends LayerArmorBase<ModelBiped> {
    private RendererLivingEntity<?> rendererIn;
    private static final FieldWrapper<RendererLivingEntity<?>> renderer = new FieldWrapper(Animations.isObfuscated ? "field_177190_a" : "renderer", LayerArmorBase.class);

    public CustomLayerBipedArmor(RendererLivingEntity<?> rendererIn) {
        super(rendererIn);
        this.rendererIn = rendererIn;
    }

    public void setRenderer(RendererLivingEntity<?> rendererIn) {
        renderer.setFinal(this, rendererIn);
        this.rendererIn = rendererIn;
    }

    protected void initArmor() {
        this.field_177189_c = new ModelBiped(0.5f);
        this.field_177186_d = new ModelBiped(1.0f);
    }

    @Override
    protected void func_177179_a(ModelBiped p_177179_1_, int p_177179_2_) {

    }

    protected void setModelPartVisible(ModelBiped p_177179_1_, int p_177179_2_) {
        this.setModelVisible(p_177179_1_);
        switch (p_177179_2_) {
            case 1: {
                p_177179_1_.bipedRightLeg.showModel = true;
                p_177179_1_.bipedLeftLeg.showModel = true;
                return;
            }
            case 2: {
                p_177179_1_.bipedBody.showModel = true;
                p_177179_1_.bipedRightLeg.showModel = true;
                p_177179_1_.bipedLeftLeg.showModel = true;
                return;
            }
            case 3: {
                p_177179_1_.bipedBody.showModel = true;
                p_177179_1_.bipedRightArm.showModel = true;
                p_177179_1_.bipedLeftArm.showModel = true;
                return;
            }
            case 4: {
                p_177179_1_.bipedHead.showModel = true;
                p_177179_1_.bipedHeadwear.showModel = true;
            }
        }
    }

    protected void setModelVisible(ModelBiped p_177194_1_) {
        p_177194_1_.setInvisible(false);
    }

    protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, int slot, ModelBiped model) {
        return ForgeHooksClient.getArmorModel(entity, itemStack, slot, model);
    }

    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 4);
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 3);
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 2);
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 1);
    }

    private void renderLayer(EntityLivingBase entitylivingbaseIn, float p_177182_2_, float p_177182_3_, float p_177182_4_, float p_177182_5_, float p_177182_6_, float p_177182_7_, float p_177182_8_, int armorSlot) {
        ItemStack itemstack = this.getCurrentArmor(entitylivingbaseIn, armorSlot);
        if (itemstack == null) return;
        if (!(itemstack.getItem() instanceof ItemArmor)) return;
        ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
        ModelBiped model = this.func_177175_a(armorSlot);
        model.setModelAttributes(this.rendererIn.getMainModel());
        model.setLivingAnimations(entitylivingbaseIn, p_177182_2_, p_177182_3_, p_177182_4_);
        model = this.getArmorModelHook(entitylivingbaseIn, itemstack, armorSlot, model);
        this.setModelPartVisible(model, armorSlot);
        boolean flag = this.isSlotForLeggings(armorSlot);
        this.rendererIn.bindTexture(this.getArmorResource(entitylivingbaseIn, itemstack, flag ? 2 : 1, null));
        int i = itemarmor.getColor(itemstack);
        float colorR = 1.0f;
        float colorG = 1.0f;
        float colorB = 1.0f;
        float alpha = 1.0f;
        if (i != -1) {
            float f = (float)(i >> 16 & 255) / 255.0f;
            float f1 = (float)(i >> 8 & 255) / 255.0f;
            float f2 = (float)(i & 255) / 255.0f;
            GlStateManager.color((float)(colorR * f), (float)(colorG * f1), (float)(colorB * f2), (float) alpha);
            ModelMethods.setRotationAnglesModelBiped(model, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_, (Entity)entitylivingbaseIn);
            ModelMethods.renderModelBiped(model, (Entity)entitylivingbaseIn, p_177182_8_);
            this.rendererIn.bindTexture(this.getArmorResource((Entity)entitylivingbaseIn, itemstack, flag ? 2 : 1, "overlay"));
        }
        GlStateManager.color((float) colorR, (float) colorG, (float) colorB, (float) alpha);
        ModelMethods.setRotationAnglesModelBiped(model, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_, (Entity)entitylivingbaseIn);
        ModelMethods.renderModelBiped(model, (Entity)entitylivingbaseIn, p_177182_8_);
        if (!itemstack.hasEffect()) return;
        this.renderGlint(entitylivingbaseIn, model, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
    }

    private boolean isSlotForLeggings(int armorSlot) {
        return armorSlot == 2;
    }

    private void renderGlint(EntityLivingBase entitylivingbaseIn, ModelBiped modelbaseIn, float p_177183_3_, float p_177183_4_, float p_177183_5_, float p_177183_6_, float p_177183_7_, float p_177183_8_, float p_177183_9_) {
        float f = (float)entitylivingbaseIn.ticksExisted + p_177183_5_;
        this.rendererIn.bindTexture(ENCHANTED_ITEM_GLINT_RES);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc((int)514);
        GlStateManager.depthMask((boolean)false);
        float f1 = 0.5f;
        GlStateManager.color((float)f1, (float)f1, (float)f1, (float)1.0f);
        int i = 0;
        do {
            if (i >= 2) {
                GlStateManager.matrixMode((int)5890);
                GlStateManager.loadIdentity();
                GlStateManager.matrixMode((int)5888);
                GlStateManager.enableLighting();
                GlStateManager.depthMask((boolean)true);
                GlStateManager.depthFunc((int)515);
                GlStateManager.disableBlend();
                return;
            }
            GlStateManager.disableLighting();
            GlStateManager.blendFunc((int)768, (int)1);
            float f2 = 0.76f;
            GlStateManager.color((float)(0.5f * f2), (float)(0.25f * f2), (float)(0.8f * f2), (float)1.0f);
            GlStateManager.matrixMode((int)5890);
            GlStateManager.loadIdentity();
            float f3 = 0.33333334f;
            GlStateManager.scale((float)f3, (float)f3, (float)f3);
            GlStateManager.rotate((float)(30.0f - (float)i * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.translate((float)0.0f, (float)(f * (0.001f + (float)i * 0.003f) * 20.0f), (float)0.0f);
            GlStateManager.matrixMode((int)5888);
            ModelMethods.renderModelBiped(modelbaseIn, (Entity)entitylivingbaseIn, p_177183_9_);
            ++i;
        } while (true);
    }
}

