package com.tfar.lootcapacitortooltips;

import crazypants.enderio.endergy.capacitor.ItemEndergyCapacitor;
import crazypants.enderio.endergy.capacitor.ItemTotemicCapacitor;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

import static com.tfar.lootcapacitortooltips.LootCapacitorTooltips.s1;
import static com.tfar.lootcapacitortooltips.LootCapacitorTooltips.s3;
import static com.tfar.lootcapacitortooltips.Tooltips.getColor;
import static com.tfar.lootcapacitortooltips.Tooltips.showTotemicDurability;

public class EndergyTooltips {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ItemEndergyCapacitor && !(stack.getItem() instanceof ItemTotemicCapacitor)) {
            float value = getCapacitorValue(stack);
            ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
            tooltip.add(I18n.format(s1) + " " + TextFormatting.fromColorIndex(getColor(value)) + value);
        } else if (stack.getItem() instanceof ItemTotemicCapacitor) {
            int uses = stack.getMaxDamage() - stack.getItemDamage();
            ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
            float value = (float) (3.5 + .5 * level);
            if (value > 5)
                tooltip.add(I18n.format(s1) + " " + TextFormatting.WHITE + TextFormatting.BOLD + value);
            else
                tooltip.add(I18n.format(s1) + " " + TextFormatting.fromColorIndex(getColor(value)) + value);
            if (showTotemicDurability)
                tooltip.add(I18n.format(s3 + "uses") + " " + TextFormatting.fromColorIndex(getUsesColor(uses)) + uses);
        }
    }

    public float getCapacitorValue(ItemStack stack) {
        String key = stack.getItem().getTranslationKey();
        switch (key){
            case "item.item_capacitor_silver": return 1;
            case "item.item_capacitor_energetic_silver": return 2;
            case "item.item_capacitor_vivid":return 3;
            case "item.item_capacitor_crystalline":return 3.5f;
            case "item.item_capacitor_melodic":return 4;
            case "item.item_capacitor_stellar":return 5;
            case "item.item_capacitor_grainy":return 1;
        }
        return 0;
    }

    public int getUsesColor(int uses){
        if (uses>400)return 10;
        if (uses>300)return 14;
        if (uses>200)return 6;
        if (uses>100)return 12;
        return 4;
    }
}