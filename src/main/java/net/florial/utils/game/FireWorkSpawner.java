package net.florial.utils.game;

import net.florial.Florial;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;


public class FireWorkSpawner implements Listener {



    public static void spawn(int amount, Color color, Color fade, Player p){

        Location loc = p.getLocation();

        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.setPower(3);
        fwm.addEffect(FireworkEffect.builder().withColor(color).flicker(true).with(FireworkEffect.Type.BALL_LARGE).with(FireworkEffect.Type.BURST).with(FireworkEffect.Type.BALL).withFade(fade).build());

        fw.setFireworkMeta(fwm);

        Bukkit.getScheduler().runTaskLater((Florial.getInstance()), () -> {for(int num = 0; num < amount; num++){fw.playEffect(EntityEffect.FIREWORK_EXPLODE);fw.detonate();}}, 40L);
    }

    @EventHandler
    public void FireWorkDisableDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Firework) e.setCancelled(true);
    }
}

