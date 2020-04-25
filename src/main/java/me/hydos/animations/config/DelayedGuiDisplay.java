/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.EventBus
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package me.hydos.animations.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DelayedGuiDisplay {
    private int delayTicks;
    private Minecraft mcClient;
    private GuiScreen screen;

    public DelayedGuiDisplay(int delayTicks, GuiScreen screen) {
        this.delayTicks = delayTicks;
        this.mcClient = Minecraft.getMinecraft();
        this.screen = screen;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals((Object)TickEvent.Phase.START)) {
            return;
        }
        if (--this.delayTicks > 0) return;
        this.mcClient.displayGuiScreen(this.screen);
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
}

