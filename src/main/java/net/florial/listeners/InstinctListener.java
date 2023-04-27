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
    public void sneakyInstinct(PlayerToggleSneakEvent e) {

        if (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getUpgrades() == null) return;
        if (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getUpgrades().get(Upgrade.SNEAKY) == null) return;

        if (e.isSneaking()) {
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 1, false, false, true));
        } else {

            e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    @EventHandler
    public void adrenaLinInstinct(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player p)) return;

        if (Florial.getPlayerData().get(e.getEntity().getUniqueId()).getUpgrades() == null) return;
        if (Florial.getPlayerData().get(e.getEntity().getUniqueId()).getUpgrades().get(Upgrade.NEARDEATH) == null) return;

        e.setCancelled(true);

        p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, (float) 0.4, 1);

        if (GetChance.chanceOf(50)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1, false, false, true));

        } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 1, false, false, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 2, false, false, true));

        }

        Cooldown.addCooldown("c1", p, 300);


    }

    @EventHandler
    public void feetInstinct(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player p) ||
                e.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades() == null) return;

        if  (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades().get(Upgrade.FALL) == null) return;

        e.setCancelled(true);


    }

    @EventHandler
    public void strikerInstinct(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player p)) return;

        if (Florial.getPlayerData().get(e.getDamager().getUniqueId()).getUpgrades() == null) return;

        if (Florial.getPlayerData().get(e.getDamager().getUniqueId()).getUpgrades().get(Upgrade.STRIKER) == null) return;

        if (Cooldown.isOnCooldown("c3", p)) return;

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1, false, false, true));


        Cooldown.addCooldown("c3", p, 30);
    }

    @EventHandler
    public void resistantInstinct(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player p)) return;

        if (e.getCause() != EntityDamageEvent.DamageCause.FIRE
        && e.getCause() != EntityDamageEvent.DamageCause.LAVA
        && e.getCause() != EntityDamageEvent.DamageCause.FREEZE) return;

        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades() == null) return;

        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades().get(Upgrade.RESISTANT) != null) e.setCancelled(true);


    }

    @EventHandler
    public void resistantInstinct(EntityDamageByEntityEvent e) {

        if (!(e.getEntity() instanceof Player p)) return;

        if (!(e.getDamager() instanceof Zoglin
        && (!(e.getDamager() instanceof Witch)))) return;

        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades() == null) return;
        if (Florial.getPlayerData().get(p.getUniqueId()).getUpgrades().get(Upgrade.FLESHEATER) == null) return;

        e.setCancelled(true);

        e.setDamage(2);
        p.setFoodLevel(20);


    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {

        if (Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getUpgrades() == null) return;
        if (Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getUpgrades().get(Upgrade.SOPHISTICATION) != null) event.setAmount(event.getAmount() * 3);

    }
}
