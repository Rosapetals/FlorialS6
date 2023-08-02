package net.florial.species.impl.usermade;

import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Enderwing extends Species implements Listener {
    public Enderwing(int id) {
        super("Enderwing", id, 20, false, null);
    }


    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                2));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }

    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.HUNGER, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.REGENERATION, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, false, false, true)));

    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>(Species.boneFoods);
    }

    @EventHandler
    public void teleportOnEye(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR
                || event.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR
                || Cooldown.isOnCooldown("c1", event.getPlayer())
                || Florial.getOngoingDuel().get(event.getPlayer().getUniqueId()) != null) return;

        event.getPlayer().teleport(event.getPlayer().getTargetBlock(null, 50).getLocation());
        Cooldown.createCooldown("c1", event.getPlayer(), 5);


    }


    @EventHandler
    public void powerfulRoar(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c2", event.getPlayer())
                || event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR
                || event.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR
                || Florial.getOngoingDuel().get(event.getPlayer().getUniqueId()) != null) return;


        Player player = event.getPlayer();

        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);

        player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10)
                .stream()
                .filter(entity -> !(entity instanceof Player) && entity instanceof LivingEntity)
                .forEach(entity -> {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 30, 2));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * 30, 0));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 30, 4));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 30, 1));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 30, 1));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 30, 0));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 30, 0));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 30, 1));
                });

        Cooldown.createCooldown("c2", player, 120);
    }

    @EventHandler
    public void shootFireBall(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c3", event.getPlayer())
                || event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR
                || event.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR
                || Florial.getOngoingDuel().get(event.getPlayer().getUniqueId()) != null) return;


        Player player = event.getPlayer();

        Location location = player.getEyeLocation();
        Vector direction = location.getDirection();

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {

                if (count >= 10) {
                    cancel();
                    return;
                }

                Fireball fireball = player.launchProjectile(Fireball.class, direction);
                fireball.setIsIncendiary(false);
                fireball.setTicksLived(80);

                count++;
            }
        }.runTaskTimer(Florial.getInstance(), 20L, 20L);

        Cooldown.createCooldown("c3", player, 10);
    }

    @EventHandler
    public void pigManBuffs(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player)
                || (!(event.getDamager() instanceof LivingEntity))
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this) return;

        LivingEntity damager = (LivingEntity) event.getDamager();

        if (((Player) event.getEntity()).getHealth() > 11) {
            damager.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 120, 0));

        }

        if (damager instanceof Piglin) {

            damager.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 120, 0));
            damager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 120, 1));
            damager.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 120, 0));

        }
    }
}
