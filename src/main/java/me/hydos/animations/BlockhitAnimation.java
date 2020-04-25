/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.MapItemRenderer
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ItemModelMesher
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.renderer.vertex.VertexFormat
 *  net.minecraft.client.resources.model.IBakedModel
 *  net.minecraft.client.resources.model.ModelManager
 *  net.minecraft.client.resources.model.ModelResourceLocation
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumWorldBlockLayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.MapData
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.model.pipeline.LightUtil
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Project
 */
package me.hydos.animations;

import me.hydos.animations.util.FieldWrapper;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

public class BlockhitAnimation {
    private static final FieldWrapper<Float> prevEquippedProgress = new FieldWrapper<>(Animations.isObfuscated ? "field_78451_d" : "prevEquippedProgress", ItemRenderer.class);
    private static final FieldWrapper<Float> equippedProgress = new FieldWrapper<>(Animations.isObfuscated ? "field_78454_c" : "equippedProgress", ItemRenderer.class);
    private static final FieldWrapper<Float> fovModifierHandPrev = new FieldWrapper<>(Animations.isObfuscated ? "field_78506_S" : "fovModifierHandPrev", EntityRenderer.class);
    private static final FieldWrapper<Float> fovModifierHand = new FieldWrapper<>(Animations.isObfuscated ? "field_78507_R" : "fovModifierHand", EntityRenderer.class);
    private static final FieldWrapper<Boolean> cloudFog = new FieldWrapper<>(Animations.isObfuscated ? "field_78500_U" : "cloudFog", EntityRenderer.class);
    private static final FieldWrapper<ItemModelMesher> modelMesher = new FieldWrapper<>(Animations.isObfuscated ? "field_175059_m" : "itemModelMesher", RenderItem.class);
    private int farPlaneDistance;
    private ItemStack itemToRender;
    private final Minecraft mc = Minecraft.getMinecraft();
    private final GameSettings gameSettings;
    private EntityRenderer entityRenderer;
    private ItemRenderer itemRenderer;
    private RenderManager renderManager;
    private ItemModelMesher itemModelMesher;
    private boolean isOF;
    private boolean init;
    private KeyBinding zoom;
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public BlockhitAnimation() {
        this.gameSettings = this.mc.gameSettings;
        this.isOF = false;
        this.init = false;
    }

    private void init() {
        if (!this.init) {
            this.init = true;
            KeyBinding[] k = this.gameSettings.keyBindings;
            for (KeyBinding keyBinding : k) {
                if (!keyBinding.getKeyDescription().equals("Zoom") && !keyBinding.getKeyDescription().equals("of.key.zoom"))
                    continue;
                this.isOF = true;
                this.zoom = keyBinding;
                System.out.println("Found Zoom key");
                break;
            }
            this.entityRenderer = this.mc.entityRenderer;
            this.itemRenderer = this.mc.getItemRenderer();
            this.renderManager = this.mc.getRenderManager();
        }
        this.itemModelMesher = modelMesher.get(this.mc.getRenderItem());
        this.farPlaneDistance = this.gameSettings.renderDistanceChunks << 4;
        this.itemToRender = this.mc.thePlayer.getCurrentEquippedItem();
    }

    private boolean isZoomed() {
        if (!this.isOF) return false;
        return this.zoom.isKeyDown();
    }

    @SubscribeEvent
    public void onRenderFirstHand(RenderHandEvent e) {
        this.init();
        if (this.isZoomed()) return;
        if (e.isCanceled()) return;
        if (this.mc.thePlayer.getHeldItem() == null) {
            return;
        }
        e.setCanceled(true);
        this.attemptSwing();
        this.renderHand(e.partialTicks, e.renderPass);
        this.renderWorldDirections(e.partialTicks);
    }

    private void renderHand(float partialTicks, int renderPass) {
        boolean flag;
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        float f1 = 0.07f;
        if (this.gameSettings.anaglyph) {
            GlStateManager.translate((float)(-((renderPass << 1) - 1)) * f1, 0.0f, 0.0f);
        }
        Project.gluPerspective(this.getFOVModifier(partialTicks), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05f, (float)(this.farPlaneDistance << 1));
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        if (this.gameSettings.anaglyph) {
            GlStateManager.translate((float)((renderPass << 1) - 1) * 0.1f, 0.0f, 0.0f);
        }
        GlStateManager.pushMatrix();
        this.hurtCameraEffect(partialTicks);
        if (this.gameSettings.viewBobbing) {
            this.setupViewBobbing(partialTicks);
        }
        flag = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping();
        if (!(this.gameSettings.thirdPersonView != 0 || flag || this.gameSettings.hideGUI || this.mc.playerController.isSpectator())) {
            this.entityRenderer.enableLightmap();
            this.renderItemInFirstPerson(partialTicks);
            this.entityRenderer.disableLightmap();
        }
        GlStateManager.popMatrix();
        if (this.gameSettings.thirdPersonView == 0 && !flag) {
            this.itemRenderer.renderOverlays(partialTicks);
            this.hurtCameraEffect(partialTicks);
        }
        if (!this.gameSettings.viewBobbing) return;
        this.setupViewBobbing(partialTicks);
    }

    private void renderWorldDirections(float partialTicks) {
        if (!this.gameSettings.showDebugInfo) return;
        if (this.gameSettings.hideGUI) return;
        if (this.mc.thePlayer.hasReducedDebug()) return;
        if (this.gameSettings.reducedDebugInfo) return;
        Entity entity = this.mc.getRenderViewEntity();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glLineWidth(1.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        this.orientCamera(partialTicks);
        GlStateManager.translate(0.0f, entity.getEyeHeight(), 0.0f);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 0.005, 1.0E-4, 1.0E-4), 255, 0, 0, 255);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0E-4, 1.0E-4, 0.005), 0, 0, 255, 255);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0E-4, 0.0033, 1.0E-4), 0, 255, 0, 255);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private void orientCamera(float p_78467_1_) {
        Entity entity = this.mc.getRenderViewEntity();
        float f1 = entity.getEyeHeight();
        double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)p_78467_1_;
        double d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)p_78467_1_ + (double)f1;
        double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)p_78467_1_;
        if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPlayerSleeping()) {
            f1 = (float)((double)f1 + 1.0);
            GlStateManager.translate(0.0f, 0.3f, 0.0f);
            if (!this.gameSettings.debugCamEnable) {
                BlockPos blockpos = new BlockPos(entity);
                IBlockState iblockstate = this.mc.theWorld.getBlockState(blockpos);
                ForgeHooksClient.orientBedCamera(this.mc.theWorld, blockpos, iblockstate, entity);
                GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * p_78467_1_ + 180.0f, 0.0f, -1.0f, 0.0f);
                GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * p_78467_1_, -1.0f, 0.0f, 0.0f);
            }
        } else if (this.gameSettings.thirdPersonView > 0) {
            double d3 = 4.0;
            if (this.gameSettings.debugCamEnable) {
                GlStateManager.translate(0.0f, 0.0f, (float)(-d3));
            } else {
                float f2 = entity.rotationYaw;
                float f3 = entity.rotationPitch;
                if (this.gameSettings.thirdPersonView == 2) {
                    f3 += 180.0f;
                }
                double d4 = (double)(-MathHelper.sin(f2 / 180.0f * 3.1415927f) * MathHelper.cos(f3 / 180.0f * 3.1415927f)) * d3;
                double d5 = (double)(MathHelper.cos(f2 / 180.0f * 3.1415927f) * MathHelper.cos(f3 / 180.0f * 3.1415927f)) * d3;
                double d6 = (double)(-MathHelper.sin(f3 / 180.0f * 3.1415927f)) * d3;
                for (int i = 0; i < 8; ++i) {
                    MovingObjectPosition movingobjectposition;
                    double d7;
                    float f4 = (i & 1) * 2 - 1;
                    float f5 = (i >> 1 & 1) * 2 - 1;
                    float f6 = (i >> 2 & 1) * 2 - 1;
                    if ((movingobjectposition = this.mc.theWorld.rayTraceBlocks(new Vec3(d0 + (double)(f4 *= 0.1f), d1 + (double)(f5 *= 0.1f), d2 + (double)(f6 *= 0.1f)), new Vec3(d0 - d4 + (double)f4 + (double)f6, d1 - d6 + (double)f5, d2 - d5 + (double)f6))) == null || !((d7 = movingobjectposition.hitVec.distanceTo(new Vec3(d0, d1, d2))) < d3)) continue;
                    d3 = d7;
                }
                if (this.gameSettings.thirdPersonView == 2) {
                    GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                }
                GlStateManager.rotate(entity.rotationPitch - f3, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(entity.rotationYaw - f2, 0.0f, 1.0f, 0.0f);
                GlStateManager.translate(0.0f, 0.0f, (float)(-d3));
                GlStateManager.rotate(f2 - entity.rotationYaw, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(f3 - entity.rotationPitch, 1.0f, 0.0f, 0.0f);
            }
        } else {
            GlStateManager.translate(0.0f, 0.0f, -0.1f);
        }
        if (!this.gameSettings.debugCamEnable) {
            GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * p_78467_1_, 1.0f, 0.0f, 0.0f);
            if (entity instanceof EntityAnimal) {
                EntityAnimal entityanimal = (EntityAnimal)entity;
                GlStateManager.rotate(entityanimal.prevRotationYawHead + (entityanimal.rotationYawHead - entityanimal.prevRotationYawHead) * p_78467_1_ + 180.0f, 0.0f, 1.0f, 0.0f);
            } else {
                GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * p_78467_1_ + 180.0f, 0.0f, 1.0f, 0.0f);
            }
        }
        GlStateManager.translate(0.0f, -f1, 0.0f);
        d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)p_78467_1_;
        d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)p_78467_1_ + (double)f1;
        d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)p_78467_1_;
        cloudFog.set(this.entityRenderer, this.mc.renderGlobal.hasCloudFog(d0, d1, d2, p_78467_1_));
    }

    private float getFOVModifier(float partialTicks) {
        Entity entity = this.mc.getRenderViewEntity();
        float f1 = 70.0f;
        if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getHealth() <= 0.0f) {
            float f2 = (float)((EntityLivingBase)entity).deathTime + partialTicks;
            f1 /= (1.0f - 500.0f / (f2 + 500.0f)) * 2.0f + 1.0f;
        }
        if (ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, entity, partialTicks).getMaterial() != Material.water) return f1;
        return f1 * 60.0f / 70.0f;
    }

    private void hurtCameraEffect(float p_78482_1_) {
        float f2;
        if (!(this.mc.getRenderViewEntity() instanceof EntityLivingBase)) return;
        EntityLivingBase entitylivingbase = (EntityLivingBase)this.mc.getRenderViewEntity();
        float f1 = (float)entitylivingbase.hurtTime - p_78482_1_;
        if (entitylivingbase.getHealth() <= 0.0f) {
            f2 = (float)entitylivingbase.deathTime + p_78482_1_;
            GlStateManager.rotate(40.0f - 8000.0f / (f2 + 200.0f), 0.0f, 0.0f, 1.0f);
        }
        if (f1 < 0.0f) {
            return;
        }
        f1 /= (float)entitylivingbase.maxHurtTime;
        f1 = MathHelper.sin(f1 * f1 * f1 * f1 * 3.1415927f);
        f2 = entitylivingbase.attackedAtYaw;
        GlStateManager.rotate(-f2, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-f1 * 14.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(f2, 0.0f, 1.0f, 0.0f);
    }

    private void setupViewBobbing(float p_78475_1_) {
        if (!(this.mc.getRenderViewEntity() instanceof EntityPlayer)) return;
        EntityPlayer entityplayer = (EntityPlayer)this.mc.getRenderViewEntity();
        float f1 = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
        float f2 = -(entityplayer.distanceWalkedModified + f1 * p_78475_1_);
        float f3 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * p_78475_1_;
        float f4 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * p_78475_1_;
        GlStateManager.translate(MathHelper.sin(f2 * 3.1415927f) * f3 * 0.5f, -Math.abs(MathHelper.cos(f2 * 3.1415927f) * f3), 0.0f);
        GlStateManager.rotate(MathHelper.sin(f2 * 3.1415927f) * f3 * 3.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(Math.abs(MathHelper.cos(f2 * 3.1415927f - 0.2f) * f3) * 5.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(f4, 1.0f, 0.0f, 0.0f);
    }

    public void renderItemInFirstPerson(float p_78440_1_) {
        float equippedProgress = BlockhitAnimation.equippedProgress.get(this.itemRenderer);
        float prevEquippedProgress = BlockhitAnimation.prevEquippedProgress.get(this.itemRenderer);
        float f1 = 1.0f - (prevEquippedProgress + (equippedProgress - prevEquippedProgress) * p_78440_1_);
        EntityPlayerSP entityplayersp = this.mc.thePlayer;
        float f2 = entityplayersp.getSwingProgress(p_78440_1_);
        float f3 = entityplayersp.prevRotationPitch + (entityplayersp.rotationPitch - entityplayersp.prevRotationPitch) * p_78440_1_;
        float f4 = entityplayersp.prevRotationYaw + (entityplayersp.rotationYaw - entityplayersp.prevRotationYaw) * p_78440_1_;
        this.rotateArroundXAndY(f3, f4);
        this.setLightMapFromPlayer(entityplayersp);
        this.rotateWithPlayerRotations(entityplayersp, p_78440_1_);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        if (this.itemToRender != null) {
            boolean rod;
            rod = this.itemToRender.getItem() instanceof ItemFishingRod;
            if (this.itemToRender.getItem() instanceof ItemMap) {
                this.renderItemMap(entityplayersp, f3, f1, f2);
            } else if (entityplayersp.getItemInUseCount() > 0) {
                EnumAction enumaction = this.itemToRender.getItemUseAction();
                float prevF2 = f2;
                switch (enumaction.ordinal()) {
                    case 1: {
                        this.transformFirstPersonItem(f1, 0.0f, rod);
                        break;
                    }
                    case 2: 
                    case 3: {
                        this.performDrinking(entityplayersp, p_78440_1_);
                        this.transformFirstPersonItem(f1, f2, rod);
                        break;
                    }
                    case 4: {
                        this.transformFirstPersonItem(f1, f2, rod);
                        this.doBlockTransformations();
                        break;
                    }
                    case 5: {
                        f2 = prevF2;
                        this.transformFirstPersonItem(f1, f2, rod);
                        this.doBowTransformations(p_78440_1_, entityplayersp);
                    }
                }
            } else {
                this.doItemUsedTransformations(f2);
                this.transformFirstPersonItem(f1, f2, rod);
            }
            this.renderItem(entityplayersp, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
        } else if (!entityplayersp.isInvisible()) {
            this.renderPlayerArm(entityplayersp, f1, f2);
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
    }

    public void swingItem(EntityPlayerSP entityplayersp) {
        int swingAnimationEnd = 0;
        int n = entityplayersp.isPotionActive(Potion.digSpeed) ? 6 - (1 + entityplayersp.getActivePotionEffect(Potion.digSpeed).getAmplifier()) : (swingAnimationEnd = entityplayersp.isPotionActive(Potion.digSlowdown) ? 6 + (1 + entityplayersp.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : 6);
        if (entityplayersp.isSwingInProgress && entityplayersp.swingProgressInt < swingAnimationEnd / 2) {
            if (entityplayersp.swingProgressInt >= 0) return;
        }
        entityplayersp.swingProgressInt = -1;
        entityplayersp.isSwingInProgress = true;
    }

    private static void enableStencil() {
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glStencilFunc(519, 1, 255);
        GL11.glDepthRange(-0.5, 0.5);
    }

    private static void disableStencil() {
        GL11.glDepthRange(0.0, 1.0);
        GL11.glDisable(2960);
    }

    public void renderItem(EntityLivingBase entityIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform) {
        GL11.glDisable(2929);
        if (heldStack != null) {
            Item item = heldStack.getItem();
            Block block = Block.getBlockFromItem(item);
            GlStateManager.pushMatrix();
            if (this.shouldRenderItemIn3D(heldStack)) {
                GlStateManager.scale(2.0f, 2.0f, 2.0f);
                if (this.isBlockTranslucent(block)) {
                    GlStateManager.depthMask(false);
                }
            }
            this.renderItemModelForEntity(heldStack, entityIn, transform);
            if (this.isBlockTranslucent(block)) {
                GlStateManager.depthMask(true);
            }
            GlStateManager.popMatrix();
        }
        GL11.glEnable(2929);
    }

    public boolean shouldRenderItemIn3D(ItemStack stack) {
        IBakedModel ibakedmodel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
        if (ibakedmodel == null) {
            return false;
        }
        boolean bl = ibakedmodel.isGui3d();
        return bl;
    }

    public void renderItemModelForEntity(ItemStack stack, EntityLivingBase entityToRenderFor, ItemCameraTransforms.TransformType cameraTransformType) {
        if (stack == null) return;
        if (entityToRenderFor == null) return;
        IBakedModel ibakedmodel = this.itemModelMesher.getItemModel(stack);
        if (entityToRenderFor instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityToRenderFor;
            Item item = stack.getItem();
            ModelResourceLocation modelresourcelocation = null;
            if (item == Items.fishing_rod && entityplayer.fishEntity != null) {
                modelresourcelocation = new ModelResourceLocation("fishing_rod_cast", "inventory");
            } else if (item == Items.bow && entityplayer.getItemInUse() != null) {
                int i = stack.getMaxItemUseDuration() - entityplayer.getItemInUseCount();
                if (i >= 18) {
                    modelresourcelocation = new ModelResourceLocation("bow_pulling_2", "inventory");
                } else if (i > 13) {
                    modelresourcelocation = new ModelResourceLocation("bow_pulling_1", "inventory");
                } else if (i > 0) {
                    modelresourcelocation = new ModelResourceLocation("bow_pulling_0", "inventory");
                }
            } else {
                assert item != null;
                modelresourcelocation = item.getModel(stack, entityplayer, entityplayer.getItemInUseCount());
            }
            if (modelresourcelocation != null) {
                ibakedmodel = this.itemModelMesher.getModelManager().getModel(modelresourcelocation);
            }
        }
        this.renderItemModelTransform(stack, ibakedmodel, cameraTransformType);
    }

    protected void renderItemModelTransform(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType) {
        TextureManager textureManager = this.mc.getTextureManager();
        textureManager.bindTexture(TextureMap.locationBlocksTexture);
        textureManager.getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
        this.preTransform(stack);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        model = ForgeHooksClient.handleCameraTransforms(model, cameraTransformType);
        this.renderItem(stack, model);
        GlStateManager.cullFace(1029);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        textureManager.bindTexture(TextureMap.locationBlocksTexture);
        textureManager.getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }

    public void renderItem(ItemStack stack, IBakedModel model) {
        GL11.glEnable(2929);
        if (stack != null) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
            if (model.isBuiltInRenderer()) {
                GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                GlStateManager.translate(-0.5f, -0.5f, -0.5f);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                GlStateManager.enableRescaleNormal();
                TileEntityItemStackRenderer.instance.renderByItem(stack);
            } else {
                GlStateManager.translate(-0.5f, -0.5f, -0.5f);
                this.renderModel(model, stack);
                if (stack.hasEffect()) {
                    this.renderOldEffect(model);
                }
            }
            GlStateManager.popMatrix();
        }
        GL11.glDisable(2929);
    }

    private void renderModel(IBakedModel model, ItemStack stack) {
        this.renderModel(model, -1, stack);
    }

    private void renderOldEffect(IBakedModel model) {
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(516);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(768, 1);
        this.renderManager.renderEngine.bindTexture(RES_ITEM_GLINT);
        GlStateManager.matrixMode(5890);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(768, 1);
        float var21 = 0.76f;
        GL11.glColor4f(0.5f * var21, 0.25f * var21, 0.8f * var21, 1.0f);
        GL11.glPushMatrix();
        float var22 = 0.125f;
        GL11.glScalef(var22, var22, var22);
        float var23 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0f * 8.0f;
        GL11.glTranslatef(var23, 0.0f, 0.0f);
        GL11.glRotatef(-50.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.depthFunc(514);
        this.renderModel(model, -8372020);
        GlStateManager.depthFunc(516);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(var22, var22, var22);
        var23 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0f * 8.0f;
        GL11.glTranslatef(-var23, 0.0f, 0.0f);
        GL11.glRotatef(10.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.depthFunc(514);
        this.renderModel(model, -8372020);
        GlStateManager.depthFunc(516);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }

    private void renderEffect(IBakedModel model) {
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(516);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(768, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(RES_ITEM_GLINT);
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0f, 8.0f, 8.0f);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0f / 8.0f;
        GlStateManager.translate(f, 0.0f, 0.0f);
        GlStateManager.rotate(-50.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.depthFunc(514);
        this.renderModel(model, -8372020);
        GlStateManager.depthFunc(516);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0f, 8.0f, 8.0f);
        float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0f / 8.0f;
        GlStateManager.translate(-f1, 0.0f, 0.0f);
        GlStateManager.rotate(10.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.depthFunc(514);
        this.renderModel(model, -8372020);
        GlStateManager.depthFunc(516);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }

    private void renderModel(IBakedModel model, int color) {
        this.renderModel(model, color, null);
    }

    private void renderModel(IBakedModel model, int color, ItemStack stack) {
        BlockhitAnimation.enableStencil();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.ITEM);
        EnumFacing[] arrenumFacing = EnumFacing.values();
        int n = arrenumFacing.length;
        int n2 = 0;
        do {
            if (n2 >= n) {
                this.renderQuads(worldrenderer, model.getGeneralQuads(), color, stack);
                tessellator.draw();
                BlockhitAnimation.disableStencil();
                return;
            }
            EnumFacing enumfacing = arrenumFacing[n2];
            this.renderQuads(worldrenderer, model.getFaceQuads(enumfacing), color, stack);
            ++n2;
        } while (true);
    }

    private void renderQuads(WorldRenderer renderer, List<BakedQuad> quads, int color, ItemStack stack) {
        boolean flag = color == -1 && stack != null;
        int i = 0;
        int j = quads.size();
        while (i < j) {
            BakedQuad bakedquad = quads.get(i);
            int k = color;
            if (flag && bakedquad.hasTintIndex()) {
                k = stack.getItem().getColorFromItemStack(stack, bakedquad.getTintIndex());
                if (EntityRenderer.anaglyphEnable) {
                    k = TextureUtil.anaglyphColor(k);
                }
                k |= -16777216;
            }
            LightUtil.renderQuadColor(renderer, bakedquad, k);
            ++i;
        }
    }

    private void preTransform(ItemStack stack) {
        IBakedModel ibakedmodel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
        Item item = stack.getItem();
        if (item == null) return;
        boolean flag = ibakedmodel.isGui3d();
        if (!flag) {
            GlStateManager.scale(2.0f, 2.0f, 2.0f);
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private boolean isBlockTranslucent(Block blockIn) {
        if (blockIn == null) return false;
        if (blockIn.getBlockLayer() != EnumWorldBlockLayer.TRANSLUCENT) return false;
        return true;
    }

    private void renderItemMap(AbstractClientPlayer p_178097_1_, float p_178097_2_, float p_178097_3_, float p_178097_4_) {
        float f3 = -0.4f * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * 3.1415927f);
        float f4 = 0.2f * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * 3.1415927f * 2.0f);
        float f5 = -0.2f * MathHelper.sin(p_178097_4_ * 3.1415927f);
        GlStateManager.translate(f3, f4, f5);
        float f6 = this.getMapAngleFromPitch(p_178097_2_);
        GlStateManager.translate(0.0f, 0.04f, -0.72f);
        GlStateManager.translate(0.0f, p_178097_3_ * -1.2f, 0.0f);
        GlStateManager.translate(0.0f, f6 * -0.5f, 0.0f);
        GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f6 * -85.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(0.0f, 1.0f, 0.0f, 0.0f);
        this.renderPlayerArms(p_178097_1_);
        float f7 = MathHelper.sin(p_178097_4_ * p_178097_4_ * 3.1415927f);
        yes(p_178097_4_, f7);
        GlStateManager.scale(0.38f, 0.38f, 0.38f);
        GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(0.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-1.0f, -1.0f, 0.0f);
        GlStateManager.scale(0.015625f, 0.015625f, 0.015625f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/map/map_background.png"));
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GL11.glNormal3f(0.0f, 0.0f, -1.0f);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
        worldrenderer.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
        worldrenderer.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
        worldrenderer.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
        tessellator.draw();
        MapData mapdata = Items.filled_map.getMapData(this.itemToRender, this.mc.theWorld);
        if (mapdata == null) return;
        this.mc.entityRenderer.getMapItemRenderer().renderMap(mapdata, false);
    }

    private void renderPlayerArms(AbstractClientPlayer p_178102_1_) {
        this.mc.getTextureManager().bindTexture(p_178102_1_.getLocationSkin());
        Render render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
        RenderPlayer renderplayer = (RenderPlayer)render;
        if (p_178102_1_.isInvisible()) return;
        this.renderRightArm(renderplayer);
        this.renderLeftArm(renderplayer);
    }

    private float getMapAngleFromPitch(float p_178100_1_) {
        float f1 = 1.0f - p_178100_1_ / 45.0f + 0.1f;
        f1 = MathHelper.clamp_float(f1, 0.0f, 1.0f);
        return -MathHelper.cos(f1 * 3.1415927f) * 0.5f + 0.5f;
    }

    private void renderRightArm(RenderPlayer p_180534_1_) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(54.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(64.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(-62.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(0.25f, -0.85f, 0.75f);
        p_180534_1_.renderRightArm(this.mc.thePlayer);
        GlStateManager.popMatrix();
    }

    private void renderLeftArm(RenderPlayer p_178106_1_) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(92.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(45.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(41.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(-0.3f, -1.1f, 0.45f);
        p_178106_1_.renderLeftArm(this.mc.thePlayer);
        GlStateManager.popMatrix();
    }

    private void renderPlayerArm(AbstractClientPlayer p_178095_1_, float p_178095_2_, float p_178095_3_) {
        GL11.glDisable(2929);
        float f2 = -0.3f * MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * 3.1415927f);
        float f3 = 0.4f * MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * 3.1415927f * 2.0f);
        float f4 = -0.4f * MathHelper.sin(p_178095_3_ * 3.1415927f);
        GlStateManager.translate(f2, f3, f4);
        GlStateManager.translate(0.64000005f, -0.6f, -0.71999997f);
        GlStateManager.translate(0.0f, p_178095_2_ * -0.6f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        float f5 = MathHelper.sin(p_178095_3_ * p_178095_3_ * 3.1415927f);
        float f6 = MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * 3.1415927f);
        GlStateManager.rotate(f6 * 70.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f5 * -20.0f, 0.0f, 0.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(p_178095_1_.getLocationSkin());
        GlStateManager.translate(-1.0f, 3.6f, 3.5f);
        GlStateManager.rotate(120.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(200.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        GlStateManager.translate(5.6f, 0.0f, 0.0f);
        Render render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
        RenderPlayer renderplayer = (RenderPlayer)render;
        renderplayer.renderRightArm(this.mc.thePlayer);
        GlStateManager.enableCull();
    }

    private void rotateArroundXAndY(float p_178101_1_, float p_178101_2_) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(p_178101_1_, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(p_178101_2_, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void setLightMapFromPlayer(AbstractClientPlayer p_178109_1_) {
        int i = this.mc.theWorld.getCombinedLight(new BlockPos(p_178109_1_.posX, p_178109_1_.posY + (double)p_178109_1_.getEyeHeight(), p_178109_1_.posZ), 0);
        float f = i & 65535;
        float f1 = i >> 16;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f, f1);
    }

    private void rotateWithPlayerRotations(EntityPlayerSP p_178110_1_, float p_178110_2_) {
        float f1 = p_178110_1_.prevRenderArmPitch + (p_178110_1_.renderArmPitch - p_178110_1_.prevRenderArmPitch) * p_178110_2_;
        float f2 = p_178110_1_.prevRenderArmYaw + (p_178110_1_.renderArmYaw - p_178110_1_.prevRenderArmYaw) * p_178110_2_;
        GlStateManager.rotate((p_178110_1_.rotationPitch - f1) * 0.1f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate((p_178110_1_.rotationYaw - f2) * 0.1f, 0.0f, 1.0f, 0.0f);
    }

    private void performDrinking(AbstractClientPlayer p_178104_1_, float p_178104_2_) {
        float f1 = (float)p_178104_1_.getItemInUseCount() - p_178104_2_ + 1.0f;
        float f2 = f1 / (float)this.itemToRender.getMaxItemUseDuration();
        float f3 = MathHelper.abs(MathHelper.cos(f1 / 4.0f * 3.1415927f) * 0.1f);
        if (f2 >= 0.8f) {
            f3 = 0.0f;
        }
        GlStateManager.translate(0.0f, f3, 0.0f);
        float f4 = 1.0f - (float)Math.pow(f2, 27.0);
        GlStateManager.translate(f4 * 0.6f, f4 * -0.5f, f4 * 0.0f);
        GlStateManager.rotate(f4 * 90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f4 * 10.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(f4 * 30.0f, 0.0f, 0.0f, 1.0f);
    }

    private void doBlockTransformations() {
        GlStateManager.translate(-0.5f, 0.2f, 0.0f);
        GlStateManager.rotate(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(60.0f, 0.0f, 1.0f, 0.0f);
    }

    private void doBowTransformations(float p_178098_1_, AbstractClientPlayer p_178098_2_) {
        GlStateManager.rotate(-18.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(-12.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-8.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-0.9f, 0.2f, 0.0f);
        float f1 = (float)this.itemToRender.getMaxItemUseDuration() - ((float)p_178098_2_.getItemInUseCount() - p_178098_1_ + 1.0f);
        float f2 = f1 / 20.0f;
        f2 = (f2 * f2 + f2 * 2.0f) / 3.0f;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f2 > 0.1f) {
            float f3 = MathHelper.sin((f1 - 0.1f) * 1.3f);
            float f4 = f2 - 0.1f;
            float f5 = f3 * f4;
            GlStateManager.translate(f5 * 0.0f, f5 * 0.01f, f5 * 0.0f);
        }
        GlStateManager.translate(f2 * 0.0f, f2 * 0.0f, f2 * 0.1f);
        GlStateManager.scale(1.0f, 1.0f, 1.0f + f2 * 0.2f);
    }

    private void doItemUsedTransformations(float p_178105_1_) {
        float f1 = -0.4f * MathHelper.sin(MathHelper.sqrt_float(p_178105_1_) * 3.1415927f);
        float f2 = 0.2f * MathHelper.sin(MathHelper.sqrt_float(p_178105_1_) * 3.1415927f * 2.0f);
        float f3 = -0.2f * MathHelper.sin(p_178105_1_ * 3.1415927f);
        GlStateManager.translate(f1, f2, f3);
    }

    private void transformFirstPersonItem(float p_178096_1_, float p_178096_2_, boolean rod) {
        if (rod) {
            GlStateManager.translate(0.4f, -0.42f, -0.71999997f);
        } else {
            GlStateManager.translate(0.56f, -0.52f, -0.71999997f);
        }
        GlStateManager.translate(0.0f, p_178096_1_ * -0.6f, 0.0f);
        GlStateManager.rotate(rod ? 50.0f : 45.0f, 0.0f, 1.0f, 0.0f);
        float f2 = MathHelper.sin(p_178096_2_ * p_178096_2_ * 3.1415927f);
        yes(p_178096_2_, f2);
        if (rod) {
            GlStateManager.scale(0.3f, 0.3f, 0.3f);
            return;
        }
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }

    private void yes(float p_178096_2_, float f2) {
        float f3 = MathHelper.sin(MathHelper.sqrt_float(p_178096_2_) * 3.1415927f);
        GlStateManager.rotate(f2 * -20.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f3 * -20.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(f3 * -80.0f, 1.0f, 0.0f, 0.0f);
    }

    private void attemptSwing() {
        if (this.mc.thePlayer.getItemInUseCount() <= 0) return;
        boolean mouseDown = this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.gameSettings.keyBindUseItem.isKeyDown();
        if (!mouseDown) return;
        if (this.mc.objectMouseOver == null) return;
        if (this.mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return;
        this.swingItem(this.mc.thePlayer);
    }

}

