package com.haruhifanclub.mods.haruhicore.client.render.armor;

import com.haruhifanclub.mods.haruhicore.client.model.armor.WizardCloakArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

@OnlyIn(Dist.CLIENT)
public class WizardCloakArmorRender implements IItemRenderProperties {

    public static final WizardCloakArmorRender INSTANCE = new WizardCloakArmorRender();

    @Override
    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("unchecked")
    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A defModel) {
        EntityModelSet models = Minecraft.getInstance().getEntityModels();
        ModelPart root = models.bakeLayer(WizardCloakArmorModel.LAYER_LOCATION);
        return (A) new WizardCloakArmorModel(root);
    }

}
