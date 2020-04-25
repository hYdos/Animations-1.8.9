package me.hydos.animations;

import me.hydos.animations.util.CustomLayerBipedArmor;
import me.hydos.animations.util.CustomLayerHeldItem;
import me.hydos.animations.util.FieldWrapper;
import me.hydos.animations.util.ModelMethods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.List;

public class ArmorAnimation {
    private static final DynamicTexture textureBrightness = new DynamicTexture(16, 16);
    private final Logger logger = LogManager.getLogger();
    private float partialTicks = 0.0f;
    private CustomLayerBipedArmor layerBipedArmor = new CustomLayerBipedArmor(null);
    private CustomLayerHeldItem layerHeldItem = new CustomLayerHeldItem();
    private static final FieldWrapper<List<?>> layerRenderers = new FieldWrapper(Animations.isObfuscated ? "field_177097_h" : "layerRenderers", RendererLivingEntity.class);

    public ArmorAnimation() {
        textureBrightness.updateDynamicTexture();
    }

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Pre e) {
        this.partialTicks = e.partialRenderTick;
    }

    @SubscribeEvent
    public void onRenderLiving(RenderLivingEvent.Pre e) {
        if (!(e.entity instanceof EntityPlayer)) {
            return;
        }
        if (e.entity.isPlayerSleeping()) {
            return;
        }
        e.setCanceled(true);
        ModelBase mainModel = e.renderer.getMainModel();
        List<?> layerRenderers = ArmorAnimation.layerRenderers.get(e.renderer);
        EntityLivingBase p_177093_1_ = e.entity;
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        mainModel.swingProgress = this.getSwingProgress(e.entity, this.partialTicks);
        mainModel.isRiding = e.entity.isRiding();
        mainModel.isChild = e.entity.isChild();
        try {
            float f5;
            float f2 = this.interpolateRotation(e.entity.prevRenderYawOffset, e.entity.renderYawOffset, this.partialTicks);
            float f3 = this.interpolateRotation(e.entity.prevRotationYawHead, e.entity.rotationYawHead, this.partialTicks);
            float f4 = f3 - f2;
            if (e.entity.isRiding() && e.entity.ridingEntity instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)e.entity.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, this.partialTicks);
                f4 = f3 - f2;
                f5 = MathHelper.wrapAngleTo180_float((float)f4);
                if (f5 < -85.0f) {
                    f5 = -85.0f;
                }
                if (f5 >= 85.0f) {
                    f5 = 85.0f;
                }
                f2 = f3 - f5;
                if (f5 * f5 > 2500.0f) {
                    f2 += f5 * 0.2f;
                }
            }
            float f9 = e.entity.prevRotationPitch + (e.entity.rotationPitch - e.entity.prevRotationPitch) * this.partialTicks;
            this.renderLivingAt(e.entity, e.x, e.y, e.z);
            f5 = this.handleRotationFloat(e.entity, this.partialTicks);
            this.rotateCorpse(e.entity, f5, f2, this.partialTicks);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
            this.preRenderCallback(e.entity, this.partialTicks);
            GlStateManager.translate((float)0.0f, (float)-1.5078125f, (float)0.0f);
            float f7 = e.entity.prevLimbSwingAmount + (e.entity.limbSwingAmount - e.entity.prevLimbSwingAmount) * this.partialTicks;
            float f8 = e.entity.limbSwing - e.entity.limbSwingAmount * (1.0f - this.partialTicks);
            if (e.entity.isChild()) {
                f8 *= 3.0f;
            }
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GlStateManager.enableAlpha();
            mainModel.setLivingAnimations(e.entity, f8, f7, this.partialTicks);
            mainModel.setRotationAngles(f8, f7, f5, f4, f9, 0.0625f, (Entity)e.entity);
            boolean flag = this.setDoRenderBrightness(e.entity, this.partialTicks);
            this.renderModel(e.entity, f8, f7, f5, f4, f9, 0.0625f, mainModel, e.renderer);
            if (flag) {
                this.unsetBrightness();
            }
            GlStateManager.depthMask((boolean)true);
            if (!((EntityPlayer)e.entity).isSpectator()) {
                for (Object o : layerRenderers) {
                    LayerRenderer layerrenderer = (LayerRenderer) o;
                    boolean redarmor = (layerrenderer.shouldCombineTextures() || layerrenderer.toString().contains("LayerBipedArmor") || layerrenderer.toString().startsWith("bkx@"));
                    boolean flag_2 = this.setBrightness(p_177093_1_, this.partialTicks, redarmor);
                    if (layerrenderer instanceof LayerBipedArmor && !(layerrenderer instanceof LayerVillagerArmor)) {
                        this.layerBipedArmor.setRenderer(e.renderer);
                        this.layerBipedArmor.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f9, 0.0625f);
                    } else if (layerrenderer instanceof LayerHeldItem) {
                        this.layerHeldItem.setRenderer(e.renderer);
                        this.layerHeldItem.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f9, 0.0625f);
                    } else {
                        layerrenderer.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f9, 0.0625f);
                    }
                    if (!flag_2) continue;
                    this.unsetBrightness();
                }
            }
            GlStateManager.disableRescaleNormal();
        }
        catch (Exception exception) {
            this.logger.error("Couldn't render e.entity", (Throwable)exception);
        }
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
        e.renderer.renderName(e.entity, e.x, e.y, e.z);
    }

    private float getSwingProgress(EntityLivingBase p_77040_1_, float p_77040_2_) {
        return p_77040_1_.getSwingProgress(p_77040_2_);
    }

    protected float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
        float f3 = p_77034_2_ - p_77034_1_;
        do {
            if (!(f3 < -180.0f)) {
                while (f3 >= 180.0f) {
                    f3 -= 360.0f;
                }
                return p_77034_1_ + p_77034_3_ * f3;
            }
            f3 += 360.0f;
        } while (true);
    }

    protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
        GlStateManager.translate((float)((float)p_77039_2_), (float)((float)p_77039_4_), (float)((float)p_77039_6_));
    }

    protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
        return (float)p_77044_1_.ticksExisted + p_77044_2_;
    }

    protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        GlStateManager.rotate((float)(180.0f - p_77043_3_), (float)0.0f, (float)1.0f, (float)0.0f);
        if (p_77043_1_.deathTime > 0) {
            float f3 = ((float)p_77043_1_.deathTime + p_77043_4_ - 1.0f) / 20.0f * 1.6f;
            if ((f3 = MathHelper.sqrt_float((float)f3)) > 1.0f) {
                f3 = 1.0f;
            }
            GlStateManager.rotate((float)(f3 * this.getDeathMaxRotation(p_77043_1_)), (float)0.0f, (float)0.0f, (float)1.0f);
            return;
        }
        String s = EnumChatFormatting.getTextWithoutFormattingCodes((String)p_77043_1_.getName());
        if (s == null) return;
        if (!s.equals("Dinnerbone")) {
            if (!s.equals("Grumm")) return;
        }
        if (p_77043_1_ instanceof EntityPlayer) return;
        if (p_77043_1_ instanceof EntityPlayer) {
            if (!((EntityPlayer)p_77043_1_).isWearing(EnumPlayerModelParts.CAPE)) return;
        }
        GlStateManager.translate((float)0.0f, (float)(p_77043_1_.height + 0.1f), (float)0.0f);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
    }

    protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
        return 90.0f;
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
    }

    protected boolean setScoreTeamColor(EntityLivingBase p_177088_1_, RendererLivingEntity<?> renderer) {
        String s;
        ScorePlayerTeam scoreplayerteam;
        int i = 16777215;
        if (p_177088_1_ instanceof EntityPlayer && (scoreplayerteam = (ScorePlayerTeam)p_177088_1_.getTeam()) != null && (s = FontRenderer.getFormatFromString((String)scoreplayerteam.getColorPrefix())).length() >= 2) {
            i = renderer.getFontRendererFromRenderManager().getColorCode(s.charAt(1));
        }
        float f1 = (float)(i >> 16 & 255) / 255.0f;
        float f2 = (float)(i >> 8 & 255) / 255.0f;
        float f = (float)(i & 255) / 255.0f;
        GlStateManager.disableLighting();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.color((float)f1, (float)f2, (float)f, (float)1.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        return true;
    }

    protected void renderModel(EntityLivingBase entityIn, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scale, ModelBase mainModel, RendererLivingEntity<?> renderer) {
        boolean flag1;
        boolean flag = !entityIn.isInvisible();
        boolean bl = flag1 = !flag && !entityIn.isInvisibleToPlayer((EntityPlayer)Minecraft.getMinecraft().thePlayer);
        if (!flag) {
            if (!flag1) return;
        }
        if (!this.bindEntityTexture((Entity)entityIn, renderer)) {
            return;
        }
        if (flag1) {
            GlStateManager.pushMatrix();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
            GlStateManager.depthMask((boolean)false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc((int)770, (int)771);
            GlStateManager.alphaFunc((int)516, (float)0.003921569f);
        }
        if (mainModel instanceof ModelPlayer) {
            ModelMethods.setRotationAnglesModelPlayer((ModelPlayer)mainModel, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale, (Entity)entityIn);
            ModelMethods.renderModelPlayer((ModelPlayer)mainModel, (Entity)entityIn, scale);
        } else if (mainModel instanceof ModelBiped) {
            ModelMethods.setRotationAnglesModelBiped((ModelBiped)mainModel, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale, (Entity)entityIn);
            ModelMethods.renderModelBiped((ModelBiped)mainModel, (Entity)entityIn, scale);
        } else {
            mainModel.render((Entity)entityIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale);
        }
        if (!flag1) return;
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        GlStateManager.popMatrix();
        GlStateManager.depthMask((boolean)true);
    }

    protected boolean bindEntityTexture(Entity entity, RendererLivingEntity<?> renderer) {
        ResourceLocation resourcelocation = this.getEntityTexture(entity);
        if (resourcelocation == null) {
            return false;
        }
        renderer.bindTexture(resourcelocation);
        return true;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.func_180594_a((AbstractClientPlayer)entity);
    }

    protected ResourceLocation func_180594_a(AbstractClientPlayer p_180594_1_) {
        return p_180594_1_.getLocationSkin();
    }

    protected void unsetScoreTeamColor() {
        GlStateManager.enableLighting();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    protected boolean setDoRenderBrightness(EntityLivingBase p_177090_1_, float p_177090_2_) {
        return this.setBrightness(p_177090_1_, p_177090_2_, true);
    }

    protected boolean setBrightness(EntityLivingBase p_177092_1_, float p_177092_2_, boolean p_177092_3_) {
        boolean flag2;
        float f1 = p_177092_1_.getBrightness(p_177092_2_);
        int i = this.getColorMultiplier(p_177092_1_, f1, p_177092_2_);
        boolean flag1 = (i >> 24 & 255) > 0;
        boolean bl = flag2 = p_177092_1_.hurtTime > 0 || p_177092_1_.deathTime > 0;
        if (!flag1 && !flag2) {
            return false;
        }
        if (!flag1 && !p_177092_3_) {
            return false;
        }
        FloatBuffer field_177095_g = GLAllocation.createDirectFloatBuffer((int)4);
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GL11.glTexEnvi((int)8960, (int)8704, (int)OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_RGB, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_RGB, (int)OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_RGB, (int)OpenGlHelper.GL_PRIMARY_COLOR);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_ALPHA, (int)7681);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_ALPHA, (int)OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_ALPHA, (int)770);
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GL11.glTexEnvi((int)8960, (int)8704, (int)OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_RGB, (int)OpenGlHelper.GL_INTERPOLATE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_RGB, (int)OpenGlHelper.GL_CONSTANT);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_RGB, (int)OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE2_RGB, (int)OpenGlHelper.GL_CONSTANT);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND2_RGB, (int)770);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_ALPHA, (int)7681);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_ALPHA, (int)OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_ALPHA, (int)770);
        field_177095_g.position(0);
        if (flag2) {
            field_177095_g.put(1.0f);
            field_177095_g.put(0.0f);
            field_177095_g.put(0.0f);
            field_177095_g.put(0.3f);
        } else {
            float f2 = (float)(i >> 24 & 255) / 255.0f;
            float f3 = (float)(i >> 16 & 255) / 255.0f;
            float f4 = (float)(i >> 8 & 255) / 255.0f;
            float f5 = (float)(i & 255) / 255.0f;
            field_177095_g.put(f3);
            field_177095_g.put(f4);
            field_177095_g.put(f5);
            field_177095_g.put(1.0f - f2);
        }
        field_177095_g.flip();
        GL11.glTexEnv((int)8960, (int)8705, (FloatBuffer)field_177095_g);
        GlStateManager.setActiveTexture((int)OpenGlHelper.GL_TEXTURE2);
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture((int)textureBrightness.getGlTextureId());
        GL11.glTexEnvi((int)8960, (int)8704, (int)OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_RGB, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_RGB, (int)OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_RGB, (int)OpenGlHelper.lightmapTexUnit);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_ALPHA, (int)7681);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_ALPHA, (int)OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_ALPHA, (int)770);
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        return true;
    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
        return 0;
    }

    protected void unsetBrightness() {
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GL11.glTexEnvi((int)8960, (int)8704, (int)OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_RGB, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_RGB, (int)OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_RGB, (int)OpenGlHelper.GL_PRIMARY_COLOR);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_ALPHA, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_ALPHA, (int)OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_ALPHA, (int)OpenGlHelper.GL_PRIMARY_COLOR);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_ALPHA, (int)770);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_ALPHA, (int)770);
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GL11.glTexEnvi((int)8960, (int)8704, (int)OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_RGB, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_RGB, (int)5890);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_RGB, (int)OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_ALPHA, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_ALPHA, (int)770);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_ALPHA, (int)5890);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.setActiveTexture((int)OpenGlHelper.GL_TEXTURE2);
        GlStateManager.disableTexture2D();
        GlStateManager.bindTexture((int)0);
        GL11.glTexEnvi((int)8960, (int)8704, (int)OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_RGB, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND1_RGB, (int)768);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_RGB, (int)5890);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE1_RGB, (int)OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_COMBINE_ALPHA, (int)8448);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_OPERAND0_ALPHA, (int)770);
        GL11.glTexEnvi((int)8960, (int)OpenGlHelper.GL_SOURCE0_ALPHA, (int)5890);
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }
}

