package com.haruhifanclub.mods.haruhicore.common.item;

import java.util.function.Supplier;
import com.haruhifanclub.mods.haruhicore.HaruhiCore;
import com.haruhifanclub.mods.haruhicore.common.block.BlockRegistry;
import com.haruhifanclub.mods.haruhicore.common.item.base.HCMusicDiscItem;
import com.haruhifanclub.mods.haruhicore.common.item.impl.*;
import com.haruhifanclub.mods.haruhicore.common.itemgroup.ItemGroupRegistry;
import com.haruhifanclub.mods.haruhicore.common.sound.SoundEventRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, HaruhiCore.MOD_ID);

    private static RegistryObject<Item> register(String id, Supplier<? extends Item> sup) {
        return ITEMS.register(id, sup);
    }

    // Technical items
    public static RegistryObject<Item> ICON_ITEM = register("icon", ModIconItem::new);

    // Common items
    public static RegistryObject<Item> REINFORCEMENT_STONE_ITEM = register("reinforcement_stone", ReinforcementStoneItem::new);
    public static RegistryObject<Item> EPIC_REINFORCEMENT_STONE_ITEM = register("epic_reinforcement_stone", EpicReinforcementStoneItem::new);

    // Block items
    public static RegistryObject<Item> SOS_BADGE_SLAB_BLOCK = register("sos_badge_slab", () -> new BlockItem(BlockRegistry.SOS_BADGE_SLAB_BLOCK.get(), new Item.Properties().tab(ItemGroupRegistry.itemGroup)));
    public static RegistryObject<Item> DANCHOU_CONE_BLOCK = register("danchou_cone", DanchouConeBlockItem::new);


    // HaruhiCore V2 //

    // Armors
    public static RegistryObject<Item> WIZARD_HAT_ITEM = register("wizard_hat", WizardHatItem::new);
    public static RegistryObject<Item> YUKIS_WIZARD_HAT_ITEM = register("yukis_wizard_hat", YukisWizardHatItem::new);
    public static RegistryObject<Item> WIZARD_CLOAK_ITEM = register("wizard_cloak", WizardCloakItem::new);
    public static RegistryObject<Item> YUKIS_WIZARD_CLOAK_ITEM = register("yukis_wizard_cloak", YukisWizardCloakItem::new);
    public static RegistryObject<Item> MAID_OUTFIT_ITEM = register("maid_outfit", MaidOutfitItem::new);
    public static RegistryObject<Item> MIKURUS_MAID_OUTFIT_ITEM = register("mikurus_maid_outfit", MikurusMaidOutfitItem::new);
    public static RegistryObject<Item> MIKURUS_CONTACT_ITEM = register("mikurus_contact", MikurusContactItem::new);

    // Weapons
    public static RegistryObject<Item> BASEBALL_BAT_ITEM = register("baseball_bat", BaseballBatItem::new);
    public static RegistryObject<Item> GUIDED_BASEBALL_BAT_ITEM = register("guided_baseball_bat", GuidedBaseballBatItem::new);
    public static RegistryObject<Item> YUKIS_WIZARD_WAND_ITEM = register("yukis_wizard_wand", YukisWizardWandItem::new);
    public static RegistryObject<Item> INFERIOR_WIZARD_WAND_ITEM = register("inferior_wizard_wand", () -> new WizardWandItem(1));
    public static RegistryObject<Item> ORDINARY_WIZARD_WAND_ITEM = register("ordinary_wizard_wand", () -> new WizardWandItem(2));
    public static RegistryObject<Item> EXCELLENT_WIZARD_WAND_ITEM = register("excellent_wizard_wand", () -> new WizardWandItem(3));
    public static RegistryObject<Item> RARE_WIZARD_WAND_ITEM = register("rare_wizard_wand", () -> new WizardWandItem(4));
    public static RegistryObject<Item> EPIC_WIZARD_WAND_ITEM = register("epic_wizard_wand", () -> new WizardWandItem(5));


    // Music Discs
    public static RegistryObject<Item> MUSIC_DISC_ITEM_IN_THE_SUMMERTIME = register("music_disc_in_the_summertime", () -> new HCMusicDiscItem(SoundEventRegistry.MUSIC_DISC_IN_THE_SUMMERTIME));
    public static RegistryObject<Item> MUSIC_DISC_ITEM_SOMEDAY_IN_THE_RAIN = register("music_disc_someday_in_the_rain", () -> new HCMusicDiscItem(SoundEventRegistry.MUSIC_DISC_SOMEDAY_IN_THE_RAIN));
    public static RegistryObject<Item> MUSIC_DISC_ITEM_VOX_IN_BOX = register("music_disc_vox_in_box", () -> new HCMusicDiscItem(SoundEventRegistry.MUSIC_DISC_VOX_IN_BOX));
    public static RegistryObject<Item> MUSIC_DISC_ITEM_HALO = register("music_disc_halo", () -> new HCMusicDiscItem(SoundEventRegistry.MUSIC_DISC_HALO));

    public static RegistryObject<Item> HOURGLASS_ITEM = register("hourglass", HourglassItem::new);
    public static RegistryObject<Item> TPDD_ITEM = register("tpdd", TpddItem::new);
}
