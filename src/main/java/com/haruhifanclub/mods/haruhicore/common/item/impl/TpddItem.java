package com.haruhifanclub.mods.haruhicore.common.item.impl;

import java.util.Collection;
import com.haruhifanclub.mods.haruhicore.common.item.base.HCHourglassItem;
import org.auioc.mods.ahutils.utils.game.EffectUtils;
import org.auioc.mods.ahutils.utils.game.MCTimeUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TpddItem extends HCHourglassItem {

    public TpddItem() {}

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 20;
    }

    @Override
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.SPEAR;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return checkNBT(itemStack);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.getCount() != 1) {
            return ActionResult.fail(ItemStack.EMPTY);
        }

        if (player.isSteppingCarefully()) {
            return write(level, player, itemStack);
        } else {
            if (!checkNBT(itemStack)) {
                return ActionResult.pass(itemStack);
            }
            player.startUsingItem(hand);
            return ActionResult.consume(itemStack);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World level, LivingEntity player) {
        if (!(player instanceof PlayerEntity)) {
            return itemStack;
        }

        return read(level, (PlayerEntity) player, itemStack);
    }

    private static boolean checkNBT(ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getTag().contains("tpdd");
    }

    private ActionResult<ItemStack> write(World level, PlayerEntity player, ItemStack itemStack) {
        { // Cooldown
            CooldownTracker cooldownTracker = player.getCooldowns();
            if (cooldownTracker.isOnCooldown(this)) {
                return ActionResult.pass(itemStack);
            }
            cooldownTracker.addCooldown(this, 20);
        }

        { // Process NBT
            if (checkNBT(itemStack)) {
                itemStack.removeTagKey("tpdd");
            }

            { // Write NBT
                CompoundNBT nbt = new CompoundNBT();

                nbt.putUUID("player", player.getUUID());

                nbt.putFloat("health", player.getHealth());

                { // Food
                    CompoundNBT food_nbt = new CompoundNBT();
                    player.getFoodData().addAdditionalSaveData(food_nbt);
                    nbt.put("food", food_nbt);
                }

                { // Effects
                    ListNBT effects_nbt = new ListNBT();

                    Collection<EffectInstance> effects = player.getActiveEffects();
                    for (EffectInstance effect : effects) {
                        CompoundNBT effect_nbt = new CompoundNBT();
                        effect_nbt.putString("id", effect.getEffect().getRegistryName().toString());
                        effect_nbt.putInt("duration", effect.getDuration());
                        effect_nbt.putInt("amplifier", effect.getAmplifier());
                        effects_nbt.add(effect_nbt);
                    }

                    nbt.put("effects", effects_nbt);
                }

                { // Time
                    long[] time = MCTimeUtils.getTime(level);

                    CompoundNBT time_nbt = new CompoundNBT();
                    time_nbt.putLong("day", time[0]);
                    time_nbt.putLong("game", time[1]);
                    time_nbt.putLong("real", time[2]);

                    nbt.put("time", time_nbt);
                }

                itemStack.addTagElement("tpdd", nbt);
            }
        }

        if (!level.isClientSide()) {
            super.broadcastTime(level, player);
        }

        return ActionResult.sidedSuccess(itemStack, level.isClientSide());
    }

    private ItemStack read(World level, PlayerEntity player, ItemStack itemStack) {
        {
            CompoundNBT nbt = itemStack.getTag().getCompound("tpdd");

            {
                player.setHealth(nbt.getFloat("health"));
                player.getFoodData().readAdditionalSaveData(nbt.getCompound("food"));
            }

            {
                player.removeAllEffects();
                ListNBT effects_nbt = nbt.getList("effects", 10);
                if (!effects_nbt.isEmpty()) {
                    for (int i = 0; i < effects_nbt.size(); i++) {
                        CompoundNBT effect_nbt = effects_nbt.getCompound(i);
                        player.addEffect(EffectUtils.getEffectInstance(effect_nbt));
                    }
                }
            }

            itemStack.removeTagKey("tpdd");
        }

        return itemStack;
    }

}
