package org.auioc.mods.ahutils.utils;

import java.util.Random;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public interface EnchUtils {
    static void enchantAll(ListNBT enchantments) {
        for (int i = 0; i < enchantments.size(); i++) {
            CompoundNBT nbt = enchantments.getCompound(i);
            nbt.putShort("lvl", (short) (nbt.getShort("lvl") + 1));
        }
    }

    static void enchantOne(ListNBT enchantments, int index) {
        CompoundNBT nbt = enchantments.getCompound(index);
        nbt.putShort("lvl", (short) (nbt.getShort("lvl") + 1));
    }

    static void enchantRandom(ListNBT enchantments) {
        int enchCount = enchantments.size();
        int index = (new Random()).nextInt((enchCount - 0) + 1) + 0;
        CompoundNBT nbt = enchantments.getCompound(index);
        nbt.putShort("lvl", (short) (nbt.getShort("lvl") + 1));
    }
}