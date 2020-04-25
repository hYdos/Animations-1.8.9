/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package me.hydos.animations.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class CustomLayerHeldItem
implements LayerRenderer<EntityLivingBase> {
    private RendererLivingEntity<?> livingEntityRenderer;

    public void setRenderer(RendererLivingEntity<?> renderer) {
        this.livingEntityRenderer = renderer;
    }

    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        boolean isBlocking;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem();
        if (itemstack == null) return;
        GlStateManager.pushMatrix();
        ModelBiped model = (ModelBiped)this.livingEntityRenderer.getMainModel();
        boolean bl = isBlocking = model.heldItemRight == 3;
        if (model.isChild) {
            float f = 0.5f;
            GlStateManager.translate((float)0.0f, (float)0.625f, (float)0.0f);
            GlStateManager.rotate((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.scale((float)f, (float)f, (float)f);
        }
        model.postRenderArm(0.0625f);
        GlStateManager.translate((float)-0.0625f, (float)0.4375f, (float)0.0625f);
        if (entitylivingbaseIn instanceof EntityPlayer && ((EntityPlayer)entitylivingbaseIn).fishEntity != null) {
            itemstack = new ItemStack((Item)Items.fishing_rod, 0);
        }
        Item item = itemstack.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();
        if (item instanceof ItemBlock && Block.getBlockFromItem((Item)item).getRenderType() == 2) {
            GlStateManager.translate((float)0.0f, (float)0.1875f, (float)-0.3125f);
            GlStateManager.rotate((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f1 = 0.375f;
            GlStateManager.scale((float)(-f1), (float)(-f1), (float)f1);
        }
        if (entitylivingbaseIn.isSneaking()) {
            GlStateManager.translate((float)0.0f, (float)0.203125f, (float)0.0f);
        }
        if (isBlocking) {
            GlStateManager.rotate((float)-45.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.rotate((float)20.0f, (float)1.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)-30.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON);
        GlStateManager.popMatrix();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}

