package net.florial.species.impl.usermade;

import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GalacticResearcher extends Species implements Listener {

    public GalacticResearcher(int id) {
        super("Galactic Researcher", id, 30, false, null);
    }


    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0, false, false, true)));
    }


    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }

    @EventHandler
    public void spyGlass(PlayerInteractEvent e) {

        if (e.getAction() != Action.LEFT_CLICK_AIR
        || Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() != this
        || e.getPlayer().getInventory().getItemInMainHand().getType() != Material.SPYGLASS
            || Cooldown.isOnCooldown("c1", e.getPlayer())) return;

        Player p = e.getPlayer();

        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set galactic");
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
            Bukkit.dispatchCommand(p, "galactic");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission unset galactic");
        }, 40L);

        Cooldown.addCooldown("c1", p, 300);
    }

    @EventHandler
    public void sunFists(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player) || (!(e.getEntity() instanceof LivingEntity ent))) return;

        if (Florial.getPlayerData().get(e.getDamager().getUniqueId()).getSpecies() == null
        || Florial.getPlayerData().get(e.getDamager().getUniqueId()).getSpecies() != this) return;

        ent.setFireTicks(120);
        ent.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 500, 0, false, false, true));
    }

}
