package com.tfar.lootcapacitortooltips;

import crazypants.enderio.base.capacitor.ItemCapacitor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
@Config(modid = "lootcapacitortooltips")
public class Tooltips {
    @Config.Name("Enable Flavor Text?")
    public static boolean enableFlavorText = false;
    @Config.Name("Precision")
    public static int round = 2;

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

            tooltip.add("Global Level: " + TextFormatting.fromColorIndex(getColor(level)) + roundAvoid(level,round));

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
                tooltip.add("Painting Machine: " + TextFormatting.fromColorIndex(getColor(painter)) + roundAvoid(painter,round));
            if (combustion > 0)
                tooltip.add("Combustion Generator: " + TextFormatting.fromColorIndex(getColor(combustion)) + roundAvoid(combustion,round));
            if (enhanced_combustion > 0)
                tooltip.add("Enhanced Combustion Generator: " + TextFormatting.fromColorIndex(getColor(enhanced_combustion)) + roundAvoid(enhanced_combustion,round));
            if (alloy_smelter > 0)
                tooltip.add("Alloy Smelter: " + TextFormatting.fromColorIndex(getColor(alloy_smelter)) + roundAvoid(alloy_smelter,round));
            if (vat > 0)
                tooltip.add("The Vat: " + TextFormatting.fromColorIndex(getColor(vat))+roundAvoid(vat,round));
            if (farm_station > 0)
                tooltip.add("Farming Station Size: " + TextFormatting.fromColorIndex(getColor(farm_station))+roundAvoid(farm_station,round));
            if (slice_and_splice > 0)
                tooltip.add("Slice & Splice: " + TextFormatting.fromColorIndex(getColor(slice_and_splice))+roundAvoid(slice_and_splice,round));
            if (energy_intake > 0)
                tooltip.add("Energy Input: " + TextFormatting.fromColorIndex(getColor(energy_buffer))+roundAvoid(energy_buffer,round));
            if (sag_mill > 0)
                tooltip.add("SAG Mill: " + TextFormatting.fromColorIndex(getColor(sag_mill))+roundAvoid(sag_mill,round));
            if (stirling_generator > 0)
                tooltip.add("Stirling Generator: " + TextFormatting.fromColorIndex(getColor(stirling_generator))+roundAvoid(stirling_generator,round));
            if (powered_spawner > 0)
                tooltip.add("Powered Spawner Speed: " + TextFormatting.fromColorIndex(getColor(powered_spawner))+roundAvoid(powered_spawner,round));
            if (soul_binder > 0)
                tooltip.add("Soul Binder: " + TextFormatting.fromColorIndex(getColor(soul_binder))+roundAvoid(soul_binder,round));
            if (energy_buffer > 0)
                tooltip.add("Energy Capacity: " + TextFormatting.fromColorIndex(getColor(energy_buffer))+roundAvoid(energy_buffer,round));
            if (area > 0)
                tooltip.add("Area: " + TextFormatting.fromColorIndex(getColor(area))+roundAvoid(area,round));
        }
    }
    public static double roundAvoid(float value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
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