package me.hydos.animations;

import me.hydos.animations.proxy.CommonProxy;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

@Mod(modid="animations", name="Orange's 1.7 Animations", useMetadata=true, acceptedMinecraftVersions="[1.8.9]")
public class Animations {
    public static boolean isObfuscated;
    @SidedProxy(clientSide="com.orangemarshall.animations.proxy.ClientProxy", serverSide="com.orangemarshall.animations.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        isObfuscated = this.isObfuscated();
        proxy.preInit(e);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        new Timer().schedule(new TimerTask(){

            @Override
            public void run() {
                if (Minecraft.getMinecraft().thePlayer == null) return;
                ChatComponentText chatComponent1 = new ChatComponentText(EnumChatFormatting.DARK_GRAY.toString() + "Animations > " + (Object)EnumChatFormatting.GRAY + "Make sure to check out /animations if you run into any issues.".replace(" ", " " + (Object)EnumChatFormatting.GRAY));
                Minecraft.getMinecraft().thePlayer.addChatComponentMessage((IChatComponent)chatComponent1);
                MinecraftForge.EVENT_BUS.unregister((Object)Animations.this);
            }
        }, 2000L);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    private boolean isObfuscated() {
        try {
            Minecraft.class.getDeclaredField("logger");
            return false;
        }
        catch (NoSuchFieldException noSuchFieldException) {
            return true;
        }
        catch (SecurityException e1) {
            e1.printStackTrace();
        }
        return true;
    }

}

