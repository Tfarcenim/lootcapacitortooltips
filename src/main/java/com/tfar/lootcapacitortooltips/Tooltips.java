package com.tfar.lootcapacitortooltips;

import crazypants.enderio.api.capacitor.CapabilityCapacitorData;
import crazypants.enderio.base.capacitor.ItemCapacitor;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static com.tfar.lootcapacitortooltips.LootCapacitorTooltips.*;

@Config(modid = "lootcapacitortooltips")
public class Tooltips {
    @Config.Name("Enable Flavor Text?")
    public static boolean enableFlavorText = false;
    @Config.Name("Precision")
    public static int round = 2;
    @Config.Name("Show Levels of Regular Capacitors?")
    public static boolean showRegularCapacitors = true;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        //if loot capacitor
        if (stack.getItem() instanceof ItemCapacitor && (stack.getItemDamage() == 3 || stack.getItemDamage() == 4)) {
            NBTTagCompound nbt = stack.getTagCompound();
            ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();

            if (!enableFlavorText) tooltip.remove(3);

            NBTTagCompound nbtCap = nbt.getCompoundTag("eiocap");

            float level = nbtCap.getFloat("level");

            if (level > 5)tooltip.add(I18n.format(s1) + " " + TextFormatting.WHITE + TextFormatting.BOLD + roundAvoid(level,round));
            else if (level>=1)tooltip.add(I18n.format(s1) + " " + TextFormatting.fromColorIndex(getColor(level)) + roundAvoid(level,round));
            else tooltip.add(I18n.format(s1) + " " + TextFormatting.DARK_RED + TextFormatting.BOLD + roundAvoid(level,round));

            Map<String,Float> machines= new TreeMap<>();

            machines.put("painter",nbtCap.getFloat(s2 + "painter/use"));
            machines.put("combustion", nbtCap.getFloat(s2 + "combustion_generator/gen"));
            machines.put("enhanced_combustion" , nbtCap.getFloat(s2 + "enhanced_combustion_generator/gen"));
            machines.put("alloy_smelter" , nbtCap.getFloat(s2 + "alloy_smelter/use"));
            machines.put("vat" , nbtCap.getFloat(s2 + "vat/use"));
            machines.put("farm_station" , nbtCap.getFloat(s2 + "farm_station/bonus_size"));
            machines.put("slice_and_splice" , nbtCap.getFloat(s2 + "slice_and_splice/use"));
            machines.put("energy_intake" , nbtCap.getFloat("energy_intake"));
            machines.put("sag_mill" , nbtCap.getFloat(s2 + "sag_mill/use"));
            machines.put("stirling_generator" , nbtCap.getFloat(s2 + "stirling_generator/gen"));
            machines.put("powered_spawner" , nbtCap.getFloat(s2 + "powered_spawner/speed"));
            machines.put("soul_binder" , nbtCap.getFloat(s2 + "soul_binder/use"));
            machines.put("energy_buffer" , nbtCap.getFloat("energy_buffer"));
            machines.put("area" , nbtCap.getFloat("area"));

            for (Map.Entry<String,Float> machine: machines.entrySet()){
                Float value = machine.getValue();
                if (value==0)continue;
                String key = machine.getKey();
                if (value>=1)
                tooltip.add(I18n.format(s3+key) + " " + TextFormatting.fromColorIndex(getColor(value))+ roundAvoid(value,round));
                else
                tooltip.add(I18n.format(s3+key) + " " + TextFormatting.DARK_RED + TextFormatting.BOLD + roundAvoid(value,round));
            }
        }
        else if (stack.hasCapability(CapabilityCapacitorData.getCapNN(),null) && showRegularCapacitors) {
            int l = stack.getItemDamage()+1;
            ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
                    tooltip.add(I18n.format(s1) + " " + TextFormatting.fromColorIndex(getColor(l))+ l);
            }
    }

    public static double roundAvoid(float value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static int getColor(float key) {
        int color = (int) Math.floor(key);
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
                return 15;
            }
        }
        return -1;
    }

}