package net.florial.species.disguises;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Morph {

    /**
    positions:
    sitting
    sleeping
    example for setting species: disguise(p, "fox", "", false, "");
    example for modifying position: disguise(p, "", "sitting", true, "");
    example for taking them out of sitting/sleeping: disguise(p, "", "sitting", false, "");

     */
    public static void activate(Player p, String type, String position, Boolean state, String age) {
        if (!position.isBlank()) {
            Bukkit.dispatchCommand(p, "modifydisguise set" + position + " " + state);
            if (state) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1000, false, false, true));
            } else {p.removePotionEffect(PotionEffectType.SLOW);}
            return;
        }
        Bukkit.dispatchCommand(p, "disguise " + type + " " + age);
        Bukkit.dispatchCommand(p, "modifydisguise setType RED");

    }
}
