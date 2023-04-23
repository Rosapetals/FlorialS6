package net.florial.features.enemies.impl;

import net.florial.features.enemies.Mob;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Wisps extends Mob implements Listener {
    public Wisps(EntityType entity) {
        super(EntityType.WITCH, EntityType.CREEPER, 15, 2, 12, List.of(new ItemStack(Material.BONE)));

    }

}