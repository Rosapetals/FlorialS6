package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class NekoRyu extends Species implements Listener {
    public NekoRyu(int id) {
        super("NekoRyu", id, 30, true, DisguiseType.WOLF);
    }


    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                1, 2));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "Summon", "spawn more mobs w/ flowers"));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>((Species.boneFoods));
    }


    @EventHandler
    public void suckBlood(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof LivingEntity entity)
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c2", event.getPlayer())) return;

        Player player = event.getPlayer();

        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 3);

        if (player.getFoodLevel() < 20) player.setFoodLevel(player.getFoodLevel() + 1);

        entity.damage(4, player);

        Cooldown.addCooldown("c2", player, 20);

    }

    @EventHandler
    public void flowerSpawn(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c1", event.getPlayer())) return;

        Player p = event.getPlayer();

        Material i = p.getInventory().getItemInMainHand().getType();

        int specific = Florial.getPlayerData().get(p.getUniqueId()).getSkills().get(Skill.SPECIFIC);

        switch(i) {

            case LILY_OF_THE_VALLEY: if (specific > 4) p.getWorld().spawnEntity(Objects.requireNonNull(event.getClickedBlock()).getLocation(), EntityType.COW);

            case DANDELION: if (specific > 2) p.getWorld().spawnEntity(Objects.requireNonNull(event.getClickedBlock()).getLocation(), EntityType.BEE);

            case POPPY: if (specific > 1) p.getWorld().spawnEntity(Objects.requireNonNull(event.getClickedBlock()).getLocation(), EntityType.CHICKEN);
        }

        Cooldown.addCooldown("c1", p, 20);

    }

    @EventHandler
    public void fireAspect(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) return;

        if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT))) event.getPlayer().getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);

    }

    @EventHandler
    public void beeImmunity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Bee)
                || (!(event.getEntity() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void sunlightVulnerability(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this) return;


        if (event.getEntity().getLocation().getBlock().getLightLevel() < 10) return;


        event.setDamage(event.getDamage() + 6);

    }
}
