/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.EventBus
 */
package me.hydos.animations.proxy;

import me.hydos.animations.ArmorAnimation;
import me.hydos.animations.BlockhitAnimation;
import me.hydos.animations.HealthAnimation;
import net.fabricmc.api.ModInitializer;

public class ModInit implements ModInitializer{


    @Override
    public void onInitialize() {
//        MinecraftForge.EVENT_BUS.register(new ArmorAnimation());
//        MinecraftForge.EVENT_BUS.register(new BlockhitAnimation());
//        MinecraftForge.EVENT_BUS.register(new HealthAnimation());
        System.out.println("Hooked Old Animations");
    }
}

