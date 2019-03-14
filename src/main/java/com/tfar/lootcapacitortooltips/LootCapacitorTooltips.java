package com.tfar.lootcapacitortooltips;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = "lootcapacitortooltips", name = "Loot Capacitor Tooltips", version = "1.2"/*,dependencies = "required:enderio"*/,
        clientSideOnly = true, acceptedMinecraftVersions = "1.12.2,")
public class LootCapacitorTooltips {
    @EventHandler
    public static void Postinit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Tooltips());}}