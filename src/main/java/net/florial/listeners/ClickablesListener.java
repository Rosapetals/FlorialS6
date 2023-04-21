package net.florial.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.features.thirst.HydrateEvent;
import net.florial.features.thirst.ThirstManager;
import net.florial.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ClickablesListener implements Listener {

    private static final List<Integer> nbtData = List.of(

            32, 34, 35, 36, 37
    );


    @EventHandler
    public void clickableUse(PlayerInteractEvent e) {

        if (e.getAction() != Action.LEFT_CLICK_AIR
            || e.getItem() == null
            || (!(nbtData.contains(NBTEditor.getInt(e.getItem(), "CustomModelData"))))) return;

        int value = NBTEditor.getInt(e.getItem(), "CustomModelData");

        switch(value) {
            case 32 -> tulipsShadow(e.getPlayer());
            case 34 -> healingOrb(e.getPlayer());
            case 35 -> infiniteCookie(e.getPlayer());
            case 36 -> waterJug(e.getPlayer());
            case 37 -> weatherManipulation(e.getPlayer());
        }
    }


    private static void tulipsShadow(Player p) {

        if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            if (!(Cooldown.isOnCooldown("c3", p))) p.removePotionEffect(PotionEffectType.INVISIBILITY);

        } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 1, false, false, true));
            Cooldown.addCooldown("c3", p, 2);

        }
    }

    private static void healingOrb(Player p) {

        if (!(Cooldown.isOnCooldown("c4", p))) p.setHealth(p.getMaxHealth());
        Cooldown.addCooldown("c4", p, 240);
    }

    private static void waterJug(Player p) {

        HydrateEvent e = new HydrateEvent(
                p,
                p.getInventory().getItemInMainHand(),
                ThirstManager.getThirst(p),
                20

        );

        Bukkit.getPluginManager().callEvent(e);
    }

    private static void weatherManipulation(Player p){

        if (Cooldown.isOnCooldown("c4", p)) return;

        p.getWorld().setStorm(!p.getWorld().hasStorm());

        Cooldown.addCooldown("c4", p, 300);



    }

    private static void infiniteCookie(Player p) {

        p.setSaturation(20);
        p.setFoodLevel(20);
    }

}
