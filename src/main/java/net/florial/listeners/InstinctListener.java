package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import net.florial.utils.Cooldown;
import net.florial.utils.math.GetChance;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zoglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InstinctListener implements Listener {




    @EventHandler
    public void resistantInstinct(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player p)) return;

        if (e.getCause() != EntityDamageEvent.DamageCause.FIRE
        && e.getCause() != EntityDamageEvent.DamageCause.LAVA
        && e.getCause() != EntityDamageEvent.DamageCause.FREEZE
                && e.getCause() != EntityDamageEvent.DamageCause.FALL
                && e.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION) return;

        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades() == null) return;

        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades().get(Upgrade.NATUREIMMUNITY) != null) e.setCancelled(true);


    }

}
