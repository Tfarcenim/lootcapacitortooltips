package com.tfar.lootcapacitortooltips;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = "lootcapacitortooltips", name = "Loot Capacitor Tooltips", version = "1.3",/*dependencies = "required:enderio",*/
        clientSideOnly = true, acceptedMinecraftVersions = "1.12.2,")
public class LootCapacitorTooltips {
    public static String s1 = "string.lootcapacitortooltips.level";
    public static String s2 = "enderio:block_";
    public static String s3 = "string.lootcapacitortooltips.";
    @EventHandler
    public static void Postinit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Tooltips());
    }}