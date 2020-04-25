/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.client.GuiIngameForge
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.hydos.animations;

import java.util.Random;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.attributes.IAttributeInstance;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.potion.Potion;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.GuiIngameForge;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthAnimation extends Screen{
    private Random rand = new Random();
    private long healthUpdateCounter;
    private int playerHealth;
    private int lastPlayerHealth;
    private float lastSystemTime;

//    @SubscribeEvent
//    public void onRender(RenderGameOverlayEvent.Pre event) {
//        if (event.type != RenderGameOverlayEvent.ElementType.HEALTH) return;
//        event.setCanceled(true);
//        this.renderHealth(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
//    }

//    public void renderHealth(int width, int height) {
//        boolean highlight;
//        this.bind(icons);
//        GlStateManager.enableBlend();
//        Minecraft mc = Minecraft.getMinecraft();
//        EntityPlayer player = (EntityPlayer)mc.getRenderViewEntity();
//        int health = MathHelper.ceiling_float_int((float)player.getHealth());
//        int updateCounter = mc.ingameGUI.getUpdateCounter();
//        boolean bl = highlight = this.healthUpdateCounter > (long)updateCounter && (this.healthUpdateCounter - (long)updateCounter) / 3L % 2L == 1L;
//        if (health < this.playerHealth && player.hurtResistantTime > 0) {
//            this.lastSystemTime = Minecraft.getSystemTime();
//            this.healthUpdateCounter = updateCounter + 20;
//        } else if (health > this.playerHealth && player.hurtResistantTime > 0) {
//            this.lastSystemTime = Minecraft.getSystemTime();
//            this.healthUpdateCounter = updateCounter + 10;
//        }
//        if ((float)Minecraft.getSystemTime() - this.lastSystemTime > 1000.0f) {
//            this.playerHealth = health;
//            this.lastPlayerHealth = health;
//            this.lastSystemTime = Minecraft.getSystemTime();
//        }
//        this.playerHealth = health;
//        int healthLast = this.lastPlayerHealth;
//        IAttributeInstance attrMaxHealth = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
//        float healthMax = (float)attrMaxHealth.getAttributeValue();
//        float absorb = player.getAbsorptionAmount();
//        int healthRows = MathHelper.ceiling_float_int((float)((healthMax + absorb) / 2.0f / 10.0f));
//        int rowHeight = Math.max(10 - (healthRows - 2), 3);
//        this.rand.setSeed(updateCounter * 312871);
//        int left = width / 2 - 91;
//        int top = height - GuiIngameForge.left_height;
//        GuiIngameForge.left_height += healthRows * rowHeight;
//        if (rowHeight != 10) {
//            GuiIngameForge.left_height += 10 - rowHeight;
//        }
//        int regen = -1;
//        if (player.isPotionActive(Potion.regeneration)) {
//            regen = updateCounter % 25;
//        }
//        int TOP = 9 * (mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? 5 : 0);
//        int BACKGROUND = highlight ? 25 : 16;
//        int MARGIN = 16;
//        if (player.isPotionActive(Potion.poison)) {
//            MARGIN += 36;
//        } else if (player.isPotionActive(Potion.wither)) {
//            MARGIN += 72;
//        }
//        float absorbRemaining = absorb;
//        int i = MathHelper.ceiling_float_int((float)((healthMax + absorb) / 2.0f)) - 1;
//        do {
//            if (i < 0) {
//                GlStateManager.disableBlend();
//                return;
//            }
//            int row = MathHelper.ceiling_float_int((float)((float)(i + 1) / 10.0f)) - 1;
//            int x = left + i % 10 * 8;
//            int y = top - row * rowHeight;
//            if (health <= 4) {
//                y += this.rand.nextInt(2);
//            }
//            if (i == regen) {
//                y -= 2;
//            }
//            this.drawTexturedModalRect(x, y, BACKGROUND, TOP, 9, 9);
//            if (absorbRemaining > 0.0f) {
//                if (absorbRemaining == absorb && absorb % 2.0f == 1.0f) {
//                    this.drawTexturedModalRect(x, y, MARGIN + 153, TOP, 9, 9);
//                } else {
//                    this.drawTexturedModalRect(x, y, MARGIN + 144, TOP, 9, 9);
//                }
//                absorbRemaining -= 2.0f;
//            } else if (i * 2 + 1 < health) {
//                this.drawTexturedModalRect(x, y, MARGIN + 36, TOP, 9, 9);
//            } else if (i * 2 + 1 == health) {
//                this.drawTexturedModalRect(x, y, MARGIN + 45, TOP, 9, 9);
//            }
//            --i;
//        } while (true);
//    }
//
//    private void bind(ResourceLocation res) {
//        Minecraft.getMinecraft().getTextureManager().bindTexture(res);
//    }
}

