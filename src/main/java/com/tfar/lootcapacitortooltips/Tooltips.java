package com.tfar.lootcapacitortooltips;

import crazypants.enderio.base.capacitor.ItemCapacitor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
@Config(modid = "lootcapacitortooltips")
public class Tooltips {
    @Config.Name("Enable Flavor Text?")
    public static boolean enableFlavorText = false;
    @Config.Name("Precision")
    public static int round = 2;
    public static int exp = 10^round;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ItemCapacitor && (stack.getItemDamage() == 3 || stack.getItemDamage() == 4)) {
            NBTTagCompound nbt = stack.getTagCompound();
            ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();

            if (!enableFlavorText) tooltip.remove(3);

            String s = "enderio:block_";
            NBTTagCompound nbtCap = nbt.getCompoundTag("eiocap");

            float level = nbtCap.getFloat("level");
            level = Math.round(level*exp)/exp;

            tooltip.add("Global Level: " + TextFormatting.fromColorIndex(getColor(level)) + level);

            float painter = nbtCap.getFloat(s + "painter/use");
            painter = Math.round(painter*exp)/exp;
            float combustion = nbtCap.getFloat(s + "combustion_generator/gen");
            combustion = Math.round(combustion*exp)/exp;
            float enhanced_combustion = nbtCap.getFloat(s + "enhanced_combustion_generator/gen");
            enhanced_combustion = Math.round(enhanced_combustion*exp)/exp;
            float alloy_smelter = nbtCap.getFloat(s + "alloy_smelter/use");
            alloy_smelter = Math.round(alloy_smelter*exp)/exp;
            float vat = nbtCap.getFloat(s + "vat/use");
            vat = Math.round(vat*exp)/exp;
            float farm_station = nbtCap.getFloat(s + "farm_station/bonus_size");
            farm_station = Math.round(farm_station*exp)/exp;
            float slice_and_splice = nbtCap.getFloat(s + "slice_and_splice/use");
            slice_and_splice = Math.round(slice_and_splice*exp)/exp;
            float energy_intake = nbtCap.getFloat("energy_intake");
            energy_intake = Math.round(energy_intake*exp)/exp;
            float sag_mill = nbtCap.getFloat(s + "sag_mill/use");
            sag_mill = Math.round(sag_mill*exp)/exp;
            float stirling_generator = nbtCap.getFloat(s + "stirling_generator/gen");
            stirling_generator = Math.round(stirling_generator*exp)/exp;
            float powered_spawner = nbtCap.getFloat(s + "powered_spawner/speed");
            powered_spawner = Math.round(powered_spawner*exp)/exp;
            float soul_binder = nbtCap.getFloat(s + "soul_binder/use");
            soul_binder = Math.round(soul_binder*exp)/exp;
            float energy_buffer = nbtCap.getFloat("energy_buffer");
            energy_buffer = Math.round(energy_buffer*exp)/exp;
            float area = nbtCap.getFloat("area");
            area = Math.round(area*exp)/exp;

            if (painter > 0)
                tooltip.add("Painting Machine: " + TextFormatting.fromColorIndex(getColor(painter)) + painter);
            if (combustion > 0)
                tooltip.add("Combustion Generator: " + TextFormatting.fromColorIndex(getColor(combustion)) + combustion);
            if (enhanced_combustion > 0)
                tooltip.add("Enhanced Combustion Generator: " + TextFormatting.fromColorIndex(getColor(enhanced_combustion)) + enhanced_combustion);
            if (alloy_smelter > 0)
                tooltip.add("Alloy Smelter: " + TextFormatting.fromColorIndex(getColor(enhanced_combustion)) + alloy_smelter);
            if (vat > 0)
                tooltip.add("The Vat: " + TextFormatting.fromColorIndex(getColor(vat))+vat);
            if (farm_station > 0)
                tooltip.add("Farming Station Size: " + TextFormatting.fromColorIndex(getColor(farm_station))+farm_station);
            if (slice_and_splice > 0)
                tooltip.add("Slice & Splice: " + TextFormatting.fromColorIndex(getColor(slice_and_splice))+slice_and_splice);
            if (energy_intake > 0)
                tooltip.add("Energy Input: " + TextFormatting.fromColorIndex(getColor(energy_buffer))+energy_buffer);
            if (sag_mill > 0)
                tooltip.add("SAG Mill: " + TextFormatting.fromColorIndex(getColor(sag_mill))+sag_mill);
            if (stirling_generator > 0)
                tooltip.add("Stirling Generator: " + TextFormatting.fromColorIndex(getColor(stirling_generator))+stirling_generator);
            if (powered_spawner > 0)
                tooltip.add("Powered Spawner Speed: " + TextFormatting.fromColorIndex(getColor(powered_spawner))+powered_spawner);
            if (soul_binder > 0)
                tooltip.add("Soul Binder: " + TextFormatting.fromColorIndex(getColor(soul_binder))+soul_binder);
            if (energy_buffer > 0)
                tooltip.add("Energy Capacity: " + TextFormatting.fromColorIndex(getColor(energy_buffer))+energy_buffer);
            if (area > 0)
                tooltip.add("Area: " + TextFormatting.fromColorIndex(getColor(area))+area);
        }
    }

    public int getColor(float key) {
        int color = (int) Math.floor((double) key);
        switch (color) {
            case 0: {
                return 4;
            }
            case 1: {
                return 12;
            }
            case 2: {
                return 14;
            }
            case 3: {
                return 10;
            }
            case 4: {
                return 11;
            }
            case 5: {
                return 9;
            }
        }
        return -1;
    }
}