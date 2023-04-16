package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BassBeast extends Species implements Listener {


    public BassBeast(int id) {
        super("BassBeast", id, 32, false, null);
    }


    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 0, false, false, true)
        ));
    }

    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                2));
    }

    @EventHandler
    public void lightningSword(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player p)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this
                || (!(event.getEntity() instanceof LivingEntity target))) return;


        if (Cooldown.isOnCooldown("c1", p)
            || (!(p.getInventory().getItemInMainHand().getType().toString().contains("SWORD")))) return;

        Location location = target.getLocation();
        World world = target.getWorld();

        world.strikeLightningEffect(location);

    }

    @EventHandler
    public void bigRoar(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c2", event.getPlayer())) return;


        Player player = event.getPlayer();

        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);

        Cooldown.addCooldown("c2", player, 10);
    }

    @EventHandler
    public void beeAllergy(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this
                || (!(event.getEntity() instanceof Bee))) return;


        event.setDamage(event.getDamage() + 6.0);
    }


    @EventHandler
    public void starchAllergy(PlayerItemConsumeEvent event) {

        if (Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this) return;

        if (event.getItem().getType() != Material.POTATO
                || event.getItem().getType() != Material.CARROT) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void rainIntolerance(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this
                || (!(event.getEntity().getWorld().hasStorm()))) return;


        event.setDamage(event.getDamage() + 6.0);
    }
}
