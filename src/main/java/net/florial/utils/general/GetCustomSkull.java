package net.florial.utils.general;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import day.dean.skullcreator.SkullCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class GetCustomSkull {
    public ItemStack getCustomSkull(String code) {
        return SkullCreator.itemFromBase64(code);
    }
}
