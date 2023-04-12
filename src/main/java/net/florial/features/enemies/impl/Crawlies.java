package net.florial.features.enemies.impl;

import net.florial.features.enemies.Mob;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class Crawlies extends Mob implements Listener {
    public Crawlies(EntityType entity) {
        super(EntityType.CAVE_SPIDER, EntityType.ZOMBIE, 35, 4, 15, List.of(new ItemStack(Material.GUNPOWDER)));

    }

}
