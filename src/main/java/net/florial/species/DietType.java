package net.florial.species;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

public enum DietType {

    COOKED_MEAT(List.of(Material.COOKED_BEEF, Material.COOKED_CHICKEN, Material.COOKED_MUTTON, Material.COOKED_PORKCHOP));

    @Getter private final List<Material> foods;

    DietType(List<Material> foods) {
        this.foods = foods;
    }
}
