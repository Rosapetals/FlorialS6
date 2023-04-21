package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class UpgradeListener implements Listener {


    @EventHandler
    public void valHallaBlessing(EntityDamageEvent e) {

        if (e.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION
                && e.getCause() != EntityDamageEvent.DamageCause.FALL
                && e.getCause() != EntityDamageEvent.DamageCause.FIRE) return;

        if (e.getEntity() instanceof Player
                && (Florial.getPlayerData().get(e.getEntity().getUniqueId()).getUpgrades().get(Upgrade.NATUREIMMUNITY))) e.setCancelled(true);

    }
}
