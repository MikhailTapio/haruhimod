package com.haruhifanclub.mods.haruhicore.client.keybinding;

import com.haruhifanclub.mods.haruhicore.HaruhiCore;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

@OnlyIn(Dist.CLIENT)
public class KeyBindingRegistry {

    private static KeyMapping create(String name, KeyConflictContext conflict, int key) {
        return new KeyMapping("key." + HaruhiCore.MOD_ID + "." + name, conflict, InputConstants.Type.KEYSYM, key, "key.category." + HaruhiCore.MOD_ID);
    }

    private static void register(KeyMapping keyBinding) {
        ClientRegistry.registerKeyBinding(keyBinding);
    }

    public static void register() {
        register(EMIT_MIKURU_BEAM_KEY);
    }


    public static final KeyMapping EMIT_MIKURU_BEAM_KEY = create("emit_mikuru_beam", KeyConflictContext.IN_GAME, GLFW.GLFW_KEY_B);

}
