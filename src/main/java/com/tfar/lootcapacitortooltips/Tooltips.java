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

            tooltip.add("Global Level: " + TextFormatting.fromColorIndex(getColor(level)) + level);

            float painter = nbtCap.getFloat(s + "painter/use");
            float combustion = nbtCap.getFloat(s + "combustion_generator/gen");
            float enhanced_combustion = nbtCap.getFloat(s + "enhanced_combustion_generator/gen");
            float alloy_smelter = nbtCap.getFloat(s + "alloy_smelter/use");
            float vat = nbtCap.getFloat(s + "vat/use");
            float farm_station = nbtCap.getFloat(s + "farm_station/bonus_size");
            float slice_and_splice = nbtCap.getFloat(s + "slice_and_splice/use");
            float energy_intake = nbtCap.getFloat("energy_intake");
            float sag_mill = nbtCap.getFloat(s + "sag_mill/use");
            float stirling_generator = nbtCap.getFloat(s + "stirling_generator/gen");
            float powered_spawner = nbtCap.getFloat(s + "powered_spawner/speed");
            float soul_binder = nbtCap.getFloat(s + "soul_binder/use");
            float energy_buffer = nbtCap.getFloat("energy_buffer");
            float area = nbtCap.getFloat("area");

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