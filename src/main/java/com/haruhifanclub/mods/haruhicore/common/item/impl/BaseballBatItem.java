package com.haruhifanclub.mods.haruhicore.common.item.impl;

import com.haruhifanclub.mods.haruhicore.common.item.base.HCBaseballBatItem;
import org.auioc.mods.ahutils.api.item.HItemTier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class BaseballBatItem extends HCBaseballBatItem {

    public BaseballBatItem() {
        super(
            new HItemTier()
                .setDurability(59)
                .setEnchantmentValue(15)
                .setRepairIngredient(() -> {
                    return Ingredient.of(Items.STICK);
                }),
            3,
            1.0F
        );
    }

}
