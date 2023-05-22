package net.florial.features.enemies.impl;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.features.enemies.Mob;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Boar extends Mob implements Listener {
    public Boar(EntityType entity) {
        super(EntityType.HOGLIN, EntityType.SKELETON, 20, 4, 10, List.of(NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PORKCHOP), "&fRaw Boar", "", false), 1, "CustomModelData")));

    }

}
