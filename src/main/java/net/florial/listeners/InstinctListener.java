package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

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
