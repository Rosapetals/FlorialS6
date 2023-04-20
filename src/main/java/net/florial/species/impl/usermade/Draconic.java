package net.florial.species.impl.usermade;

import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Draconic extends Species implements Listener {


    public Draconic(int id) {
        super("Draconic", id, 28, true, null);
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
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.ABSORPTION, 1000000, 0, false, false, true)));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>((Species.boneFoods));
    }


    @EventHandler
    public void slownessRoar(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR
        || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
        || Cooldown.isOnCooldown("c1", event.getPlayer())
        || event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) return;


        Player player = event.getPlayer();

        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);

        player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10)
                .stream()
                .filter(entity -> !entity.equals(player))
                .forEach(entity -> ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 2)));

        Cooldown.addCooldown("c1", player, 10);
    }


}
