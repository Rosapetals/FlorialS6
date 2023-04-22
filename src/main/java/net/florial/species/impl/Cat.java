package net.florial.species.impl;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.models.PlayerData;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import net.florial.utils.math.AgeFormula;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class Cat extends Species implements Listener {

    public Cat(int id) {
        super("Cat", id, 14, true, DisguiseType.CAT);

    }


    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                1));
    }
    @Override
    public HashMap<Integer, PotionEffect> specific() {

        return new HashMap<>(Map.ofEntries(
                Map.entry(2, new PotionEffect(PotionEffectType.SPEED, 1, 1, false, false, true)),
                Map.entry(3, new PotionEffect(PotionEffectType.SPEED, 1, 2, false, false, true))

        ));
    }

    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false, true)));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "Mobility", "Faster, climb, jump ability"));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>(Species.boneFoods);
    }


    @EventHandler
    public void makePurr(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (!(p.isSneaking()) || (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) || Florial.getPlayerData().get(p.getUniqueId()).getSpecies() != this || (Cooldown.isOnCooldown("c2", p))) return;

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        int ageValue = data.getAge().getIncrease();

        for (Entity ent : p.getNearbyEntities(7, 7, 7)) {
            if (e instanceof LivingEntity) ((LivingEntity) ent).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, ageValue/2, false, false, true));

        }

        Cooldown.addCooldown("c1", p, AgeFormula.get(200, ageValue));


    }

    @EventHandler
    public void nineLives(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getSpecies() != this || (Cooldown.isOnCooldown("c1", p)) || (!(p.getHealth() < 6))) return;

        e.setCancelled(true);
        p.setHealth(p.getHealth() + 7);

        p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, (float) 0.4, 1);
        p.playSound(p.getLocation(), Sound.ENTITY_CAT_BEG_FOR_FOOD, 1, 1);

        Cooldown.addCooldown("c1", p, AgeFormula.get(300, data.getAge().getIncrease()));


    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player attacker) {

            PlayerData data = Florial.getPlayerData().get(attacker.getUniqueId());

            if (data.getSpecies() != this) return;

            if (attacker.getInventory().getItemInMainHand().getType() == Material.AIR || (!(Cooldown.isOnCooldown("c1", attacker)))) {

                Location particleLoc = attacker.getLocation().clone()
                    .add(0.0, 1.0, 0.0)
                    .add(attacker.getLocation().getDirection().clone().normalize().multiply(0.75));

                attacker.spawnParticle(Particle.SWEEP_ATTACK, particleLoc, 2);

                for (Entity e : attacker.getNearbyEntities(3, 3, 3)) {
                    if (!(e instanceof LivingEntity)) continue;
                    Vector launchDirection = e.getLocation().toVector().subtract(attacker.getLocation().toVector()).normalize().multiply(1.2);
                    launchDirection.setY(0.5);
                    e.setVelocity(launchDirection);
                    ((LivingEntity) e).damage(4+data.getAge().getIncrease());
                }

                Cooldown.addCooldown("c1", attacker, 10);
            }
        }
    }

    @EventHandler
    public void catClimb(PlayerInteractEvent e) {

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK
                && Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() == this
                && (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSkills().get(Skill.SPECIFIC) > 3
                && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR)) pounce(e.getPlayer());

    }

    @EventHandler
    public void catPounce(PlayerInteractEvent e) {

        if (e.getAction() == Action.LEFT_CLICK_AIR
                && e.getPlayer().isSneaking()
                && Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() == this
                && (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSkills().get(Skill.SPECIFIC) > 4
                && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR)) pounce(e.getPlayer());

    }

    private static void pounce(Player p) {

        Vector unitVector = new Vector(0, p.getLocation().getDirection().getY(), 0).normalize();
        p.setVelocity(unitVector.multiply(5));
    }
}
